import { Module } from "vuex";
import { RootState } from "./index";
import { MypageModule, PostDir, Post } from "@/store/MypageInterface.ts"
import { Axios, LocalAxios } from "@/service/axios.service";
import router from "@/router";

const module: Module<MypageModule, RootState> = {
  namespaced: true,

  state: {
    isSidebarActive: true,
    postDirList: [],
    postDir: [],
    post: null,
    postDirId: null,
    postId: null,
    postDirName: null,
  },

  getters: {},

  mutations: {
    TOGGLE_SIDEBAR(state) {
      state.isSidebarActive = !state.isSidebarActive
    },

    SET_POSTDIR_LIST(state, postDirList: PostDir[]) {
      state.postDirList = postDirList;

    },

    ADD_POSTDIR(state, postDir: PostDir) {
      state.postDirList.push(postDir)

    },

    SET_POSTDIR(state, postDir: Post[]) {
      state.postDir = postDir;
    },

    SELECT_POSTDIR(state, { postDirId }) {
      state.postDirId = postDirId
    },

    SET_POST(state, post: Post) {
      state.post = post;
    },

    SELECT_POST(state, { postId }) {
      state.postId = postId
    }
  },

  actions: {
    FETCH_POSTDIR_LIST({ commit }) {
      Axios.instance
        .get("/api/postdir/find/user")
        .then(({ data }) => {
          commit("SET_POSTDIR_LIST", data.data)
        })
        .catch(err => console.error(err))
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
          commit("SET_POSTDIR", data.data)
        })
        .catch(err => console.error(err))
    },

    FETCH_POST({ commit }, postId: number) {
      const postData = {
        params: {
          postId
        }
      }
      Axios.instance
        .get("/api/post/find/postid", postData)
        .then(({ data }) => {
          commit("SET_POST", data.data)
        })
        .catch(err => console.error(err))
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

    UPDATE_POSTDIR({ dispatch }, { postDirId, postDirName }: { postDirId: number, postDirName: string }) {
      const postDirData = {
        params: {
          postDirId, postDirName
        }
      }
      Axios.instance
        .put("/api/postdir/update", null, postDirData)
        .then(({ data }) => {
          dispatch("FETCH_POSTDIR_LIST");
          dispatch("FETCH_POSTDIR", data.data.postDirId)
          return data.data.postDirId
        })
        .catch(err => console.error(err))

    },

    ADD_POST({ dispatch }, { postContent, postDirId, postTitle }: { postContent: string, postDirId: number, postTitle: string }) {
      const postData = {
        params: {
          postContent, postDirId, postTitle
        }
      }
      Axios.instance
        .post("/api/post/save", null, postData)
        .then(({ data }) => {
          dispatch("FETCH_POSTDIR", data.data.postDirId);
          return data.data.postDirId
        })
        .then((postDirId) => {
          router.push({ name: "PostDir", params: { postDirId } });
        })
        .catch(err => console.error(err))
    },

    UPDATE_POST({ dispatch },
      { postContent, postDirId, postId, postTitle }:
        { postContent: string, postDirId: number, postId: number, postTitle: string }) {
      const postData = {
        params: {
          postContent, postDirId, postId, postTitle
        }
      }
      Axios.instance
        .put("/api/post/update", null, postData)
        .then(({ data }) => {
          dispatch("FETCH_POSTDIR", data.data.postDirId)
        })
        .catch(err => console.error(err))
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

  }


};

export default module;
