import { Module } from "vuex";
import { RootState } from "./index";
import {
  FeedModule,
  SidebarList,
  RssList,
  Article,
  FeedList,
  Rss,
  Feed
} from "./Feed.interface";
import { Axios } from "@/service/axios.service";
import router from "@/router";

const module: Module<FeedModule, RootState> = {
  namespaced: true,
  state: {
    rssList: [],
    feedList: [],
    boardList: [],
    article: null,
    subscribeId: null,
    articleList: [],
    subsContextMenu: {
      showCtx: false,
      x: 0,
      y: 0
    },
    feedContextMenu: {
      showCtx: false,
      x: 0,
      y: 0
    }
  },

  getters: {},

  mutations: {
    SET_FEED_LIST(state, feedList: FeedList[]) {
      state.feedList = feedList;
    },

    ADD_FEED(state, feed: FeedList) {
      state.feedList.push(feed);
    },

    SET_BOARD_LIST(state, boardList: SidebarList[]) {
      state.boardList = boardList;
    },

    SET_RSS_LIST(state, rssList: Rss[]) {
      state.rssList = rssList;
    },

    // 보드 처리하는 api 아직 없음
    ADD_BOARD(state, board: SidebarList) {
      state.boardList.push(board);
    },

    SELECT_ARTICLE(state, article: Article) {
      state.article = article;
    },

    SET_SUB_CONTEXT_MENU(state, ctx) {
      state.subsContextMenu.showCtx = false;
      state.feedContextMenu.showCtx = false;
      state.subsContextMenu = ctx;
    },

    SET_FEED_CONTEXT_MENU(state, ctx) {
      state.feedContextMenu.showCtx = false;
      state.subsContextMenu.showCtx = false;
      state.feedContextMenu = ctx;
    }
  },
  actions: {
    FETCH_FEED({ commit }) {
      Axios.instance
        .get("/api/feed/list")
        .then(({ data }) => commit("SET_FEED_LIST", data.data))
        .catch(err => console.error(err));
    },

    FETCH_RSS({ commit }) {
      Axios.instance
        .get("/api/rss/list/all")
        .then(({ data }) => commit("SET_RSS_LIST", data.data))
        .catch(err => console.error(err));
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
          dispatch("FETCH_FEED");
          return data.data.feedName;
        })
        .then(feedName => {
          router.push({ name: "Feed", params: { feedName } });
        })
        .catch(err => console.error(err));
    },

    SUBSCRIBE_RSS({ dispatch }, { feedId, rss }: { feedId: number; rss: Rss }) {
      const feedData = {
        params: {
          feedId
        }
      };
      const subscribeData = {
        params: {
          rssId: rss.rssId,
          subscribeName:
            rss.rssName ||
            ["동아경제", "노컷경제", "칸경제", "", "칸IT"][rss.rssId - 1],
          feedId
        }
      };

      let deleteId: number | null = null;

      Axios.instance
        .get("/api/subscribe/find/feed", feedData)
        .then(({ data }) => {
          if (
            data.data.length &&
            data.data.some((el: Feed) => {
              deleteId = el.subscribeId;
              return el.rss.rssId === rss.rssId;
            })
          ) {
            // subscribe 취소
            const deleteData = {
              params: {
                subscribeId: deleteId
              }
            };
            Axios.instance
              .delete("/api/subscribe/delete", deleteData)
              .then(() => dispatch("FETCH_FEED"))
              .catch(err => console.error(err));
          } else {
            // subscribe
            Axios.instance
              .post("/api/subscribe/save", null, subscribeData)
              .then(() => dispatch("FETCH_FEED"))
              .catch(err => console.error(err));
          }
        });
    },
    FETCH_ARTICLE_LIST({ state }, subscribeId) {
      Axios.instance
        .get("/api/rss/item/subscribe", {
          params: { subscribeId: subscribeId }
        })
        .then(({ data }) => (state.articleList = data.data))
        .catch(err => console.error(err));
    },

    FETCH_ARTICLE_LIST_IN_FEED({ state }, feedId) {
      Axios.instance
        .get("/api/rss/item/feed", {
          params: { feedId: feedId }
        })
        .then(({ data }) => {
          state.articleList = data.data;
        })
        .catch(err => console.error(err));
    },

    UPDATE_SUBSCRIBE({ dispatch }, { feedId, subscribeId, subscribeName }) {
      const updateData = {
        params: {
          feedId,
          subscribeId,
          subscribeName
        }
      };
      Axios.instance
        .put("api/subscribe/update", null, updateData)
        .then(() => dispatch("FETCH_FEED"))
        .then(() => dispatch("FETCH_ARTICLE_LIST", subscribeId))
        .catch(err => console.error(err));
    },

    UNFOLLOW_SUBSCRIPTION({ dispatch }, subscribeId: number) {
      Axios.instance
        .delete("api/subscribe/delete", { params: { subscribeId } })
        .then(() => dispatch("FETCH_FEED"))
        .catch(err => console.error(err));
    },

    FOLLOW_SUBSCRIPTION({ dispatch }, { feedId, rssId, subscribeName }) {
      const followData = {
        params: { feedId, rssId, subscribeName }
      };
      Axios.instance
        .post("api/subscribe/save", null, followData)
        .then(() => dispatch("FETCH_FEED"))
        .catch(err => console.error(err));
    }
  }
};

export default module;
