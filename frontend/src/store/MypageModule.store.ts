import { Module } from "vuex";
import { RootState } from "./index";
import { MypageModule, PostDir, Post, Tag } from "@/store/MypageInterface.ts";
import { Axios, LocalAxios } from "@/service/axios.service";
import router from "@/router";
import QueryString from "qs";

const module: Module<MypageModule, RootState> = {
  namespaced: true,

  state: {
    isSidebarActive: true,
    isCreateFolderModalActive: false,
    postDirList: [],
    postDir: [],
    post: null,
    postDirId: null,
    postId: null,
    postDirName: null,
    postDirContextMenu: {
      showCtx: false,
      x: 0,
      y: 0
    },
    postContextMenu: {
      showCtx: false,
      x: 0,
      y: 0
    }
  },

  getters: {},

  mutations: {
    TOGGLE_SIDEBAR(state) {
      state.isSidebarActive = !state.isSidebarActive;
    },

    TOGGLE_CREATEFOLDERMODAL(state) {
      state.isCreateFolderModalActive = !state.isCreateFolderModalActive;
    },

    SET_POSTDIR_LIST(state, postDirList: PostDir[]) {
      state.postDirList = postDirList;
    },

    ADD_POSTDIR(state, postDir: PostDir) {
      state.postDirList.push(postDir);
    },

    SET_POSTDIR(state, postDir: Post[]) {
      state.postDir = postDir;
    },

    SELECT_POSTDIR(state, { postDirId }) {
      state.postDirId = postDirId;
    },

    SET_POST(state, post: Post) {
      state.post = post;
    },

    SELECT_POST(state, { postId }) {
      state.postId = postId;
    },

    SET_POSTDIR_CONTEXT_MENU(state, ctx) {
      state.postDirContextMenu.showCtx = false;
      state.postDirContextMenu = ctx;
    },

    SET_POST_CONTEXT_MENU(state, ctx) {
      state.postContextMenu.showCtx = false;
      state.postContextMenu = ctx;
    }
  },

  actions: {
    FETCH_POSTDIR_LIST({ commit }) {
      Axios.instance
        .get("/api/postdir/find/user")
        .then(({ data }) => {
          commit("SET_POSTDIR_LIST", data.data);
        })
        .catch(err => console.error(err));
    },

    FETCH_POSTDIR({ commit }, postDirId: number) {
      const postData = {
        params: {
          postDirId
        }
      };
      Axios.instance
        .get("/api/postdir/find/postdir", postData)
        .then(({ data }) => {
          commit("SET_POSTDIR", data.data);
        })
        .catch(err => console.error(err));
    },

    FETCH_POST({ commit }, postId: number) {
      const postData = {
        params: {
          postId
        }
      };
      Axios.instance
        .get("/api/post/find/postid", postData)
        .then(({ data }) => {
          commit("SET_POST", data.data);
        })
        .catch(err => console.error(err));
    },

    ADD_POSTDIR({ dispatch }, postDirName: string) {
      const postDirData = {
        params: {
          postDirName
        }
      };
      Axios.instance
        .post("/api/postdir/save", null, postDirData)
        .then(({ data }) => {
          dispatch("FETCH_POSTDIR_LIST");
          return data.data.postDirId;
        })
        .then(postDirId => {
          router.push({ name: "PostDir", params: { postDirId } });
        })
        .catch(err => console.error(err));
    },

    UPDATE_POSTDIR(
      { dispatch },
      { postDirId, postDirName }: { postDirId: number; postDirName: string }
    ) {
      const postDirData = {
        params: {
          postDirId,
          postDirName
        }
      };
      Axios.instance
        .put("/api/postdir/update", null, postDirData)
        .then(({ data }) => {
          dispatch("FETCH_POSTDIR_LIST");
          dispatch("FETCH_POSTDIR", data.data.postDirId);
          return data.data.postDirId;
        })
        .catch(err => console.error(err));
    },

    ADD_POST(
      { dispatch },
      {
        postContent,
        postDirId,
        postTitle,
        tagList
      }: {
        postContent: string;
        postDirId: number;
        postTitle: string;
        tagList: string[];
      }
    ) {
      Axios.instance
        .post(
          "/api/post/save",
          QueryString.stringify(
            {
              postContent: postContent,
              postDirId: postDirId,
              postTitle: postTitle,
              tagList: tagList
            },
            { indices: false }
          )
        )
        .then(({ data }) => {
          dispatch("FETCH_POSTDIR", data.data.postDirId);
          return data.data.postDirId;
        })
        .then(postDirId => {
          router.push({ name: "PostDir", params: { postDirId } });
        })
        .catch(err => console.error(err));
    },

    UPDATE_POST(
      { dispatch },
      {
        postContent,
        postDirId,
        postId,
        postTitle,
        tagList
      }: {
        postContent: string;
        postDirId: number;
        postId: number;
        postTitle: string;
        tagList: [];
      }
    ) {
      Axios.instance
        .put(
          "/api/post/update",
          QueryString.stringify(
            {
              postContent: postContent,
              postDirId: postDirId,
              postId: postId,
              postTitle: postTitle,
              tagList: tagList
            },
            { indices: false }
          )
        )
        .then(({ data }) => {
          dispatch("FETCH_POSTDIR", data.data.postDirId);
        })
        .catch(err => console.error(err));
    },

    DELETE_POSTDIR({ dispatch }, postDirId: number) {
      const postDirData = {
        params: {
          postDirId
        }
      };
      Axios.instance
        .delete("/api/postdir/delete", postDirData)
        .then(() => {
          dispatch("FETCH_POSTDIR_LIST");
        })
        .then(() => {
          router.push({ name: "Home" });
        })
        .catch(err => console.error(err));
    },

    DELETE_POST(
      { dispatch },
      { postId, postDirId }: { postId: number; postDirId: number }
    ) {
      const postData = {
        params: {
          postId
        }
      };
      Axios.instance
        .delete("/api/post/delete", postData)
        .then(() => {
          dispatch("FETCH_POSTDIR", postDirId);
        })
        .catch(err => console.error(err));
    }
  }
};

export default module;
