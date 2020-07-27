import { Module } from "vuex";
import { RootState } from "./index";
import { FeedModule, SidebarList } from "./Feed.interface";
import Axios from "@/service/axios.service";

const module: Module<FeedModule, RootState> = {
  namespaced: true,
  state: {
    feedList: [],
    boardList: []
  },
  getters: {},
  mutations: {
    setFeedList(state, feedList: SidebarList[]) {
      state.feedList = feedList;
    },
    addFeed(state, feed: SidebarList) {
      state.feedList.push(feed);
    },

    setBoardList(state, boardList: SidebarList[]) {
      state.boardList = boardList;
    },
    addBoard(state, board: SidebarList) {
      state.boardList.push(board);
    }
  },
  actions: {
    initData({ commit }) {
      Axios.instance.get("/data.json").then(({ data }) => {
        console.log(data);
        commit("setFeedList", data.feedList);
        commit("setBoardList", data.boardList);
      });
    }
  }
};

export default module;
