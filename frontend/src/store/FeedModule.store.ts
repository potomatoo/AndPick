import { Module } from "vuex";
import { RootState } from "./index";
import {
  FeedModule,
  SidebarList,
  RssList,
  Article,
  FeedList,
  Rss
} from "./Feed.interface";
import { Axios, LocalAxios } from "@/service/axios.service";
import router from "@/router";

const module: Module<FeedModule, RootState> = {
  namespaced: true,
  state: {
    rssList: [],
    feedList: [],
    boardList: [],
    article: null
  },
  getters: {},
  mutations: {
    setFeedList(state, feedList: FeedList[]) {
      state.feedList = feedList;
      console.log("피드리스트", state.feedList);
    },
    addFeed(state, feed: FeedList) {
      state.feedList.push(feed);
    },

    setBoardList(state, boardList: SidebarList[]) {
      state.boardList = boardList;
      console.log("보드리스트", state.boardList);
    },
    setRssList(state, rssList: Rss[]) {
      state.rssList = rssList;
      console.log("RSS리스트", state.rssList);
    },
    // 보드 처리하는 api 아직 없음
    addBoard(state, board: SidebarList) {
      state.boardList.push(board);
    },
    subscribeRss(state, { feedname, rss }: { feedname: string; rss: Rss }) {
      console.log("피드피드 작동안함", state.feedList);
      // state.feedList.forEach(feed => {
      //   if (feed.feedName === feedname) {
      //     let removeIdx = 0;
      //     if (
      //       feed.subscribeList?.length &&
      //       feed.subscribeList?.some((el, idx) => {
      //         removeIdx = idx;
      //         return el.subscribeName === rss.rssName;
      //       })
      //     ) {
      //       feed.subscribeList?.splice(removeIdx, 1);
      //     } else {
      //       feed.subscribeList?.push(rss);
      //     }
      //   }
      // });
    },
    selectArticle(state, article: Article) {
      console.log(article, state);
      state.article = article;
    }
  },
  actions: {
    FETCH_DATA({ commit }) {
      Axios.instance
        .get("/api/feed/list")
        .then(({ data }) => {
          commit("setFeedList", data.data);
          // commit("setRssList", data.rssList.data);
        })
        .catch(err => console.error(err));

      // LocalAxios.instance.get("/data.json").then(({ data }) => {
      //   console.log("초기feed데이터", data.feedList.data);
      //   console.log("초기rss데이터", data.rssList);
      //   commit("setFeedList", data.feedList.data);
      //   commit("setRssList", data.rssList.data);
      //   commit("setBoardList", data.boardList);
      // });
    },
    ADD_FEED({ dispatch }, feedName) {
      const data = {
        params: {
          feedName
        }
      };
      Axios.instance
        .post("/api/feed/save", null, data)
        .then(({ data }) => {
          dispatch("FETCH_DATA");
          return data.data.feedName;
        })
        .then(feedName => {
          router.push({ name: "Feed", params: { feedName } });
        })
        .catch(err => console.error(err));
    }
  }
};

export default module;
