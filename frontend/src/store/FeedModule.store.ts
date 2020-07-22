import { Module } from "vuex";
import { RootState } from "./index";

interface FeedModule {
  data: string;
}

const module: Module<FeedModule, RootState> = {
  namespaced: true,
  state: {
    data: "feedModule"
  },
  getters: {},
  mutations: {},
  actions: {}
};

export default module;
