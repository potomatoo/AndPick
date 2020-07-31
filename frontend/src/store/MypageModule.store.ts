import { Module } from "vuex";
import { RootState } from "./index";
import { MypageModule, PostDir, Post } from "@/store/MypageInterface.ts"
import { Axios, LocalAxios } from "@/service/axios.service";
import router from "@/router";

const module: Module<MypageModule, RootState> = {
  namespaced: true,

  state: {
    isSidebarActive: true,
    postDirList: []
  },

  getters: {},

  mutations: {
    toggleSidebar(state) {
      state.isSidebarActive = !state.isSidebarActive
    },

    setPostDirList(state, postDirList: PostDir[]) {
      state.postDirList = postDirList;
      console.log("postDir리스트", state.postDirList);
    },

    addPost(state, postDir: PostDir) {
      state.postDirList.push(postDir)
    },
  },

  actions: {
    FETCH_DATA({ commit }) {
      Axios.instance
        .get("/api/postdir/find/user")
        .then(({ data }) => {
          commit("setPostDirList", data.data)
        })
        .catch(err => console.error(err))
    },

    ADD_POSTDIR({ dispatch }, postDirName) {
      const data = {
        params: {
          postDirName
        }
      };
      Axios.instance
        .post("/api/postdir/save", null, data)
        .then(({ data }) => {
          dispatch("FETCH_DATA");
          return data.data.postDirName;
        })
        .then(postDirName => {
          router.push({ name: "FolderMain", params: { postDirName } });
        })
        .catch(err => console.error(err));
    }
  }
};

export default module;
