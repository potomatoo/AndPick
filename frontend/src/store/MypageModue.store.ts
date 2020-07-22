import { Module } from "vuex";
import { RootState } from "./index";

interface MypageModule {
  data: string;
}

const module: Module<MypageModule, RootState> = {
  namespaced: true,
  state: {
    data: "mypageModule"
  },
  getters: {},
  mutations: {},
  actions: {}
};

export default module;
