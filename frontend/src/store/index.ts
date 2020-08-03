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
      axios.defaults.headers.common["Authorization"] = token;
    },
  },
  actions: {
    postAuthData({ commit }, info) {
      axios
        .post(SERVER.URL + info.location, qs.stringify(info.data), {
          // headers: {
          // "Access-Control-Allow-Origin": "*",
          withCredentials: true,
          // },
        })
        .then((res) => {
          console.log(res);
          commit("SET_TOKEN", res.data.data["userPassword"]);
          router.push("/");
        })
        .catch((err) => {
          console.log("err", err);
          alert("아이디 또는 비밀번호가 옳지 않습니다.");
        });
    },

    signup(context, signupData) {
      axios
        .post(SERVER.URL + SERVER.ROUTES.signup, qs.stringify(signupData), {
          // headers: {
          // "Access-Control-Allow-Origin": "*",
          withCredentials: true,
          // },
        })
        .then((res) => {
          console.log("회원가입 성공", res);
          router.push("/");
        })
        .catch((err) => {
          console.log("err", err);
          alert("회원가입 실패입니다.");
        });
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

    updateUser({ getters, commit }, updateData) {
      axios
        .put(SERVER.URL + SERVER.ROUTES.updateUser, updateData, getters.config)
        .then((res) => {
          commit("SET_TOKEN", res.data.data["Authorization"]);
          router.push("/");
        })
        .catch((err) => console.log("err", err));
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
