import { Module } from "vuex";
import store, { RootState } from "./index";
import { MypageModule, SidebarList } from "@/store/MypageInterface.ts"

const module: Module<MypageModule, RootState> = {
  namespaced: true,

  state: {
    isSidebarActive: true,
    FolderList: [],

    MyArticleList: [
      {
        articleItems: [
          {
            title: "samsung",
            content: "삼성면접",
          },
          {
            title: "kakao",
            content: "카카오 기술면접"
          },
          {
            title: "LG",
            content: "LG 인적성"
          },
          {
            title: "Hyundai",
            content: "임원면접"
          }
        ]
      },
    ]
  },


  getters: {},

  mutations: {
    toggleSidebar(state) {
      state.isSidebarActive = !state.isSidebarActive
    },
    addFolder(state, folder: SidebarList) {
      state.FolderList.push(folder)
    },
    changeArticle(state, changeArticleList) {
      state.MyArticleList = changeArticleList;
    }
  },


  actions: {}
};

export default module;
