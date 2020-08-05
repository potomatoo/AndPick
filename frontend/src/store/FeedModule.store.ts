import { Module } from "vuex";
import { RootState } from "./index";
import {
  FeedModule,
  Article,
  FeedList,
  Rss,
  Feed,
  Board
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
    feed: null,
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

    SET_FEED(state, feed: FeedList) {
      state.feed = feed;
    },

    SET_BOARD_LIST(state, boardList: Board[]) {
      state.boardList = boardList;
    },

    SET_RSS_LIST(state, rssList: Rss[]) {
      state.rssList = rssList;
    },

    SET_SELECTED_SUBSCRIPTION(state, subscribeId: number) {
      state.subscribeId = subscribeId;
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
    FETCH_FEED_LIST({ commit }) {
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

    FETCH_FEED({ commit }, feedId: number) {
      Axios.instance
        .get("/api/feed/feedid", { params: { feedId } })
        .then(({ data }) => commit("SET_FEED", data.data))
        .catch(err => console.error(err));
    },

    FETCH_BOARD_LIST({ commit }) {
      Axios.instance
        .get("/api/board/find/all")
        .then(({ data }) => commit("SET_BOARD_LIST", data.data))
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
          dispatch("FETCH_FEED_LIST");
          return { feedName: data.data.feedName, feedId: data.data.feedId };
        })
        .then(({ feedId }) => {
          router.push({ name: "Feed", params: { feedId } });
        })
        .catch(err => console.error(err));
    },

    UPDATE_FEED({ dispatch, state }, { feedId, feedName }) {
      const updateData = {
        params: {
          feedId,
          feedName
        }
      };
      Axios.instance
        .put("/api/feed/put", null, updateData)
        .then(() => {
          dispatch("FETCH_FEED_LIST");
        })
        .then(() => {
          if (state.feed && state.feed.feedId === feedId) {
            dispatch("FETCH_ARTICLE_LIST_IN_FEED", feedId);
          }
        })
        .catch(err => console.error(err));
    },

    DELETE_FEED({ dispatch }, feedId) {
      Axios.instance
        .delete("/api/feed/delete", { params: { feedId } })
        .then(() => dispatch("FETCH_FEED_LIST"))
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
              .then(() => dispatch("FETCH_FEED_LIST"))
              .catch(err => console.error(err));
          } else {
            // subscribe
            Axios.instance
              .post("/api/subscribe/save", null, subscribeData)
              .then(() => dispatch("FETCH_FEED_LIST"))
              .catch(err => console.error(err));
          }
        });
    },

    FETCH_ARTICLE_LIST({ state, commit }, subscribeId) {
      Axios.instance
        .get("/api/rss/item/subscribe", { params: { subscribeId } })
        .then(({ data }) => {
          commit("SET_SELECTED_SUBSCRIPTION", subscribeId);
          state.articleList = data.data;
        })
        .catch(err => console.error(err));
    },

    FETCH_ARTICLE_LIST_IN_FEED({ state, dispatch }, feedId) {
      Axios.instance
        .get("/api/rss/item/feed", { params: { feedId } })
        .then(({ data }) => {
          dispatch("FETCH_FEED", feedId);
          state.articleList = data.data;
        })
        .catch(err => console.error(err));
    },

    UPDATE_SUBSCRIBE(
      { dispatch, state },
      { feedId, subscribeId, subscribeName }
    ) {
      const updateData = {
        params: {
          feedId,
          subscribeId,
          subscribeName
        }
      };
      Axios.instance
        .put("/api/subscribe/update", null, updateData)
        .then(() => dispatch("FETCH_FEED_LIST"))
        .then(() => {
          if (state.subscribeId === subscribeId) {
            dispatch("FETCH_ARTICLE_LIST", subscribeId);
          }
        })
        .catch(err => console.error(err));
    },

    UNFOLLOW_SUBSCRIPTION({ dispatch }, subscribeId: number) {
      Axios.instance
        .delete("/api/subscribe/delete", { params: { subscribeId } })
        .then(() => dispatch("FETCH_FEED_LIST"))
        .catch(err => console.error(err));
    },

    FOLLOW_SUBSCRIPTION({ dispatch }, { feedId, rssId, subscribeName }) {
      const followData = {
        params: { feedId, rssId, subscribeName }
      };
      Axios.instance
        .post("/api/subscribe/save", null, followData)
        .then(() => dispatch("FETCH_FEED_LIST"))
        .catch(err => console.error(err));
    },

    ADD_BOARD({ dispatch }, boardName) {
      Axios.instance
        .post("/api/board/save", null, { params: { boardName } })
        .then(() => dispatch("FETCH_BOARD_LIST"))
        .catch(err => console.error(err));
    }
  }
};

export default module;
