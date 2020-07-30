import { Module } from "vuex";
import store, { RootState } from "./index";
import { MypageModule, FolderItem, ArticleItem } from "@/store/MypageInterface.ts"

const module: Module<MypageModule, RootState> = {
  namespaced: true,

  state: {
    isSidebarActive: true,
    folderList: [
      {
        title: "kakao 면접준비",
        articleList: [
          {
            title: "kakao taxi",
            content: "택시사업이 흥행하고 있다..."
          }
        ]
      }
    ],
    myArticleList: []
  },


  getters: {},

  mutations: {
    TOGGLE_SIDEBAR(state) {
      state.isSidebarActive = !state.isSidebarActive
    },
    ADD_FOLDER(state, folder: FolderItem) {
      state.folderList.push(folder)
    },
    ADD_ARTICLE(state, articleItem: ArticleItem) {
      state.myArticleList.push(articleItem);
      console.log(state.myArticleList)
    }
  },

  actions: {}
};

export default module;
