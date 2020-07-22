import Vue from "vue";
import Vuex, { StoreOptions } from "vuex";
import feedModule from "./FeedModule.store";
import mypageModule from "./MypageModule.store";

Vue.use(Vuex);

export interface RootState {
  data: string,

}

const store: StoreOptions<RootState> = {
  modules: {
    feedModule,
    mypageModule: mypageModule
  },
  state: {
    data: "root",

  },
  getters: {},
  mutations: {},
  actions: {}
};

export default new Vuex.Store(store);
