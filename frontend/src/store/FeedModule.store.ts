import { Module } from "vuex";
import { RootState } from "./index";
import { FeedModule, SidebarList, RssList } from "./Feed.interface";
import Axios from "@/service/axios.service";

const module: Module<FeedModule, RootState> = {
  namespaced: true,
  state: {
    rssList: [],
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
    setRssList(state, rssList: RssList[]) {
      state.rssList = rssList;
    },
    addBoard(state, board: SidebarList) {
      state.boardList.push(board);
    },
    addRssToFeed(state, { feedname, rss }: { feedname: string; rss: RssList }) {
      state.feedList.forEach(feed => {
        if (feed.title === feedname) {
          let removeIdx = 0;
          if (
            feed.items?.length &&
            feed.items?.some((el, idx) => {
              removeIdx = idx;
              return el.title === rss.title;
            })
          ) {
            feed.items?.splice(removeIdx, 1);
          } else {
            feed.items?.push(rss);
          }
        }
      });
    }
  },
  actions: {
    initData({ commit }) {
      Axios.instance.get("/data.json").then(({ data }) => {
        // console.log(data);
        commit("setFeedList", data.feedList);
        commit("setBoardList", data.boardList);
        commit("setRssList", data.rssList);
      });
    }
  }
};

export default module;
