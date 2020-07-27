import Vue from "vue";
import Vuex, { StoreOptions } from "vuex";
import feedModule from "./FeedModule.store";
import mypageModule from "./MypageModule.store";

import axios, { AxiosResponse } from "axios";

import router from "@/router";
import SERVER from "@/api/spr";

import qs from "qs";

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
        .post(SERVER.URL + info.location, qs.stringify(info.data), {
          withCredentials: true,
        })

        .then((res) => {
          console.log(res, "로그인성공");
          // console.log(res.headers.common["Authorization"]);

          // commit("SET_TOKEN", res.headers["jwt-token"]);
          commit("isLogedIn", info.bool);
          // router.push("/");
        })
        .catch((err) => console.log("err", err.response));
    },

    signup(context, signupData) {
      // const info = {
      //   data: signupData,
      //   location: SERVER.ROUTES.signup,
      //   bool: true,
      // };
      // dispatch("postAuthData", info);
      axios
        .post(SERVER.URL + SERVER.ROUTES.signup, qs.stringify(signupData))
        .then((res) => {
          console.log("성공", res);
        })
        .catch((err) => console.error("err", err.response));
    },

    login({ dispatch }, loginData) {
      const info = {
        data: loginData,
        location: SERVER.ROUTES.login,
        bool: true,
      };
      dispatch("postAuthData", info);
    },
    logout({ commit }) {
      const config = {
        headers: {
          Authorization: `Token ${this.state.JWT}`,
        },
      };
      axios
        .post(SERVER.URL + SERVER.ROUTES.logout, null, config)
        .then(() => {
          commit("SET_TOKEN", null);
          commit("isLogedIn", false);
          STORAGE.removeItem("jwt-token");
          router.push("/");
        })
        .catch((err) => console.log(err.response));
    },
  },
};

export default new Vuex.Store(store);
