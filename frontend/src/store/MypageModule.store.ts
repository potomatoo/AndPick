import { Module } from "vuex";
import store, { RootState } from "./index";
import { MypageModule } from "@/store/MypageInterface.ts"

const module: Module<MypageModule, RootState> = {
  namespaced: true,

  state: {
    isSidebarActive: true,
    IsCreateFolderActive: false,
    MyFolderList: [],
    CreateFolderItem: {
      CreateFolderName: "",
    },
    MyArticleList: [{
      title: "samsung"
    },
    {
      title: "kakao"
    },
    {
      title: "LG"
    },
    {
      title: "Hyundai"
    }
    ],
  },

  getters: {},

  mutations: {
    toggleSidebar(state) {
      state.isSidebarActive = !state.isSidebarActive;
    },
    toggleCreateFolder(state) {
      state.IsCreateFolderActive = !state.IsCreateFolderActive;
    },
    addFolder(state, FolderName) {
      state.MyFolderList.push({
        title: FolderName
      })
    }
  },

  actions: {}
};

export default module;
