import { Module } from "vuex";
import { RootState } from "./index";

export interface MypageModule {
  isSidebarActive: boolean;
}

const module: Module<MypageModule, RootState> = {
  namespaced: true,

  state: {
    isSidebarActive: true
  },

  getters: {},

  mutations: {
    toggleSidebar(state) {
      state.isSidebarActive = !state.isSidebarActive;
    }
  },

  actions: {}
};

export default module;
