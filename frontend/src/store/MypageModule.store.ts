import { Module } from "vuex";
import { RootState } from "./index";


export interface MypageModule {
  // isSidebarActive: boolean,
  data: string
}

const module: Module<MypageModule, RootState> = {
  namespaced: true,
  state: {
    // isSidebarActive: false,
    data: "hi"
  },
  getters: {},
  mutations: {
    toggleSidebar(state) {
      console.log(state)
    }
  },
  actions: {

  }
};

export default module;
