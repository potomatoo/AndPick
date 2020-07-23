import Vue from "vue";
import Vuex, { StoreOptions } from "vuex";
import feedModule from "./FeedModule.store";
import mypageModule from "./MypageModule.store";

import axios, { AxiosResponse } from "axios";

import router from "@/router";
import SERVER from "@/api/spr";

const STORAGE = window.sessionStorage;

Vue.use(Vuex);

export interface RootState {
  JWT: string | null;
  isLogedIn: boolean;
}

const store: StoreOptions<RootState> = {
  modules: {
    feedModule,
    mypageModule: mypageModule,
  },
  state: {
    JWT: STORAGE.getItem("jwt-token"),
    isLogedIn: false,
  },
  getters: {},
  mutations: {
    SET_TOKEN(state, token) {
      state.JWT = token;
      STORAGE.setItem("jwt-token", token);
    },
    isLogedIn(state, bool) {
      state.isLogedIn = bool;
    },
  },
  actions: {
    postAuthData({ commit }, info) {
      axios
        .post(SERVER.URL + info.location, info.data)
        .then((res: AxiosResponse<{}>) => {
          commit("SET_TOKEN", res.headers["jwt-token"]);
          commit("isLogedIn", info.bool);
          router.push({ name: "Home " });
        })
        .catch((err) => console.log(err.response.data));
    },

    signup({ dispatch }, signupData) {
      const info = {
        data: signupData,
        location: SERVER.ROUTES.signup,
        bool: true,
      };
      dispatch("postAuthData", info);
    },

    login({ dispatch }, loginData) {
      const info = {
        data: loginData,
        location: SERVER.ROUTES.login,
        bool: true,
      };
      dispatch("postAuthData", info);
    },
  },
};

export default new Vuex.Store(store);
