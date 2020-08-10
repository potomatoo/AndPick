import Vue from "vue";
import Vuex, { StoreOptions } from "vuex";
import feedModule from "./FeedModule.store";
import mypageModule from "./MypageModule.store";

import axios from "axios";
import { Axios } from "@/service/axios.service";

import router from "@/router";
import SERVER from "@/api/spr";

import qs from "qs";

const STORAGE = window.sessionStorage;

Vue.use(Vuex);

export interface RootState {
  JWT: string | null;
  duplicate: boolean;
  userName: string | null;
}

const store: StoreOptions<RootState> = {
  modules: {
    feedModule,
    mypageModule,
  },
  state: {
    JWT: STORAGE.getItem("jwt-token"),
    duplicate: false,
    userName: null,
  },
  getters: {
    isLoggedIn: (state) => !!state.JWT,
    config: (state) => ({ headers: { Authorization: `${state.JWT}` } }),
  },
  mutations: {
    SET_TOKEN(state, token: string) {
      state.JWT = token;
      STORAGE.setItem("jwt-token", token);
    },

    SET_NAME(state, userName: string) {
      state.userName = userName;
    },

    CHECK_DUPLICATE(state) {
      state.duplicate = true;
    },
  },
  actions: {
    postAuthData({ commit }, info) {
      axios
        .post(SERVER.URL + info.location, qs.stringify(info.data), {
          withCredentials: true,
        })
        .then((res) => {
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
          withCredentials: true,
        })
        .then((res) => {
          router.push("/");
        })
        .catch((err) => {
          console.log("err", err);
          alert("회원가입 실패입니다.");
        });
    },

    checkId({ commit }, signupData) {
      axios
        .post(SERVER.URL + SERVER.ROUTES.checkid, qs.stringify(signupData), {
          withCredentials: true,
        })
        .then((res) => {
          commit("CHECK_DUPLICATE");
          alert("사용가능한 이메일 입니다.");
        })
        .catch((err) => {
          alert("이미 가입된 이메일 입니다.");
        });
    },

    login({ dispatch }, loginData) {
      const info = {
        data: loginData,
        location: SERVER.ROUTES.login,
      };
      dispatch("postAuthData", info);
    },

    social({ dispatch }, socialData) {
      const info = {
        data: socialData,
        location: SERVER.ROUTES.social,
      };
      console.log("소셜데이터", socialData);
      dispatch("postAuthData", info);
    },

    logout({ getters, commit }) {
      commit("SET_TOKEN", null);
      STORAGE.removeItem("jwt-token");
      router.push("/");
    },

    updateUser({ commit }, userName) {
      const newData = {
        params: {
          userName,
        },
      };
      Axios.instance
        .put(SERVER.URL + SERVER.ROUTES.updateUser, null, newData)
        .then(() => {
          router.push("/");
          commit("SET_NAME", userName);
        })
        .catch((err) => console.log("err", err));
    },

    deleteUser({ getters, commit }) {
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
