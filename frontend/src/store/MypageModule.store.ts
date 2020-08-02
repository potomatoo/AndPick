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
          return data.data.postDirName;
        })
        .then(postDirName => {
          router.push({ name: "PostDir", params: { postDirName } });
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

  }


};

export default module;
