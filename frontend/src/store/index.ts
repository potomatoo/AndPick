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
}

const store: StoreOptions<RootState> = {
  modules: {
    feedModule,
    mypageModule,
  },
  state: {
    JWT: STORAGE.getItem("jwt-token"),
  },
  getters: {
    isLoggedIn: (state) => !!state.JWT,
    config: (state) => ({ headers: { Authorization: `${state.JWT}` } }),
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.JWT = token;
      STORAGE.setItem("jwt-token", token);
    },
  },
  actions: {
    postAuthData({ commit }, info) {
      axios
        .post(SERVER.URL + info.location, qs.stringify(info.data), {
          withCredentials: true,
        })
        .then((res) => {
          commit("SET_TOKEN", res.data.data["Authorization"]);
          router.push("/");
        })
        .catch((err) => console.log("err", err.response));
    },

    signup(context, signupData) {
      axios
        .post(SERVER.URL + SERVER.ROUTES.signup, qs.stringify(signupData))
        .then((res) => {
          console.log("회원가입 성공", res);
        })
        .catch((err) => console.error("err", err.response));
    },

    login({ dispatch }, loginData) {
      const info = {
        data: loginData,
        location: SERVER.ROUTES.login,
      };
      dispatch("postAuthData", info);
    },
    logout({ getters, commit }) {
      console.log("로그아웃");
      axios
        .post(SERVER.URL + SERVER.ROUTES.logout, null, getters.config)
        .then(() => {
          commit("SET_TOKEN", null);
          STORAGE.removeItem("jwt-token");
          router.push("/");
        })
        .catch((err) => console.log(err.response));
    },
    deleteUser({ getters, commit }) {
      console.log("회원탈퇴");
      axios
        .post(SERVER.URL + SERVER.ROUTES.deleteUser, null, getters.config)
        .then(() => {
          commit("SET_TOKEN", null);
          STORAGE.removeItem("jwt-token");
          router.push("/");
        })
        .catch((err) => console.log(err.response));
    },
  },
};

export default new Vuex.Store(store);
