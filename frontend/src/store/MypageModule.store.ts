import { Module } from "vuex";
import { RootState } from "./index";
import { MypageModule, PostDir, Post } from "@/store/MypageInterface.ts"
import { Axios, LocalAxios } from "@/service/axios.service";

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
    addPost(state, postDir: PostDir) {
      state.postDirList.push(postDir)
    },
  },

  actions: {
    ADD_FOLDER({ commit }, folderName) {
      Axios.instance
        .post("/api/postdir/save")
        .then()
    }
  }
};

export default module;
