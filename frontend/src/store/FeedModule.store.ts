import { Module } from "vuex";
import { RootState } from "./index";
import {
  FeedModule,
  Article,
  FeedList,
  Rss,
  Feed,
  Board,
  News,
  Category,
  RssOnAdd
} from "./Feed.interface";
import { Axios } from "@/service/axios.service";

const module: Module<FeedModule, RootState> = {
  namespaced: true,
  state: {
    rssList: [],
    feedList: [],
    boardList: [],
    article: null,
    feed: null,
    board: null,
    news: null,
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
    },
    boardContextMenu: {
      showCtx: false,
      x: 0,
      y: 0
    },
    isLoading: false,
    categoryList: [],
    rssAllCount: 0
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

    SET_ARTICLE_LIST(state, articleList: Article[]) {
      state.articleList = articleList;
    },

    SET_BOARD(state, board: Board) {
      state.board = board;
    },

    SET_NEWS(state, news: News) {
      state.news = news;
    },

    SET_RSS_LIST(state, rssList: RssOnAdd[]) {
      state.rssList = rssList;
    },

    SET_SELECTED_SUBSCRIPTION(state, subscribeId: number) {
      state.subscribeId = Number(subscribeId);
    },

    SET_ARTICLE_DETAIL(state, article: Article) {
      state.article = article;
    },

    SET_SUB_CONTEXT_MENU(state, ctx) {
      state.subsContextMenu.showCtx = false;
      state.feedContextMenu.showCtx = false;
      state.boardContextMenu.showCtx = false;
      state.subsContextMenu = ctx;
    },

    SET_FEED_CONTEXT_MENU(state, ctx) {
      state.feedContextMenu.showCtx = false;
      state.subsContextMenu.showCtx = false;
      state.boardContextMenu.showCtx = false;
      state.feedContextMenu = ctx;
    },

    SET_BOARD_CONTEXT_MENU(state, ctx) {
      state.feedContextMenu.showCtx = false;
      state.subsContextMenu.showCtx = false;
      state.boardContextMenu.showCtx = false;
      state.boardContextMenu = ctx;
    },

    SET_LOADING(state) {
      state.isLoading = !state.isLoading;
    },

    SET_CATEGORY_LIST(state, categoryList: Category[]) {
      state.categoryList = categoryList;
    },

    SET_RSS_COUNT(state, count: number) {
      state.rssAllCount = count;
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
        .get("/api/rss/find/channel/all")
        .then(({ data }) => {
          commit("SET_RSS_LIST", data.data);
          commit("SET_RSS_COUNT", data.data.length);
        })
        .catch(err => console.error(err));
    },

    FETCH_FEED({ commit, dispatch }, feedId: number) {
      Axios.instance
        .get("/api/feed/feedid", { params: { feedId } })
        .then(({ data }) => {
          commit("SET_FEED", data.data);
          dispatch("FETCH_ARTICLE_LIST_IN_FEED", feedId);
        })
        .catch(err => console.error(err));
    },

    FETCH_BOARD_LIST({ commit }) {
      Axios.instance
        .get("/api/board/find/all")
        .then(({ data }) => commit("SET_BOARD_LIST", data.data))
        .catch(err => console.error(err));
    },

    ADD_FEED({ dispatch }, feedName) {
      Axios.instance
        .post("/api/feed/save", { feedName })
        .then(({ data }) => {
          dispatch("FETCH_FEED_LIST");
          return { feedName: data.data.feedName, feedId: data.data.feedId };
        })
        // .then(({ feedId }) => {
        //   router.push({ name: "Feed", params: { feedId } });
        // })
        .catch(err => console.error(err));
    },

    UPDATE_FEED({ dispatch, state, commit }, { feedId, feedName }) {
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
            dispatch("FETCH_FEED", feedId);
          }
        })
        .catch(err => console.error(err))
        .finally(() => commit("SET_LOADING"));
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
        rss,
        subscribeName:
          rss.rssName ||
          ["동아경제", "노컷경제", "칸경제", "", "칸IT"][rss.rssId - 1],
        feedId
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
              .post("/api/subscribe/save", subscribeData)
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
          commit("SET_ARTICLE_LIST", data.data);
        })
        .catch(err => console.error(err))
        .finally(() => commit("SET_LOADING"));
    },

    FETCH_ARTICLE_LIST_IN_FEED({ state, commit }, feedId) {
      Axios.instance
        .get("/api/rss/item/feed", { params: { feedId } })
        .then(({ data }) => {
          commit("SET_ARTICLE_LIST", data.data);
        })
        .catch(err => console.error(err))
        .finally(() => commit("SET_LOADING"));
    },

    UPDATE_SUBSCRIBE(
      { dispatch, state, commit },
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
        .catch(err => console.error(err))
        .finally(() => commit("SET_LOADGIN"));
    },

    UNFOLLOW_SUBSCRIPTION({ dispatch }, subscribeId: number) {
      Axios.instance
        .delete("/api/subscribe/delete", { params: { subscribeId } })
        .then(() => dispatch("FETCH_FEED_LIST"))
        .catch(err => console.error(err));
    },

    FOLLOW_SUBSCRIPTION({ dispatch }, { feedId, rss, subscribeName }) {
      Axios.instance
        .post("/api/subscribe/save", { feedId, rss, subscribeName })
        .then(() => dispatch("FETCH_FEED_LIST"))
        .catch(err => console.error(err));
    },

    ADD_BOARD({ dispatch }, boardName) {
      Axios.instance
        .post("/api/board/save", { boardName })
        .then(() => dispatch("FETCH_BOARD_LIST"))
        .catch(err => console.error(err));
    },

    FETCH_ARTICLE_LIST_IN_BOARD({ commit }, boardId) {
      Axios.instance
        .get("/api/board/find/id", { params: { boardId } })
        .then(({ data }) => {
          commit("SET_BOARD", data.data);
        })
        .catch(err => console.error(err))
        .finally(() => commit("SET_LOADING"));
    },

    UPDATE_BOARD({ dispatch, state, commit }, { boardId, boardName }) {
      Axios.instance
        .put("/api/board/update", null, { params: { boardId, boardName } })
        .then(() => dispatch("FETCH_BOARD_LIST"))
        .then(() => {
          if (state.board && state.board.boardId === boardId) {
            dispatch("FETCH_ARTICLE_LIST_IN_BOARD", boardId);
          }
        })
        .catch(err => console.error(err))
        .finally(() => commit("SET_LOADING"));
    },

    DELETE_BOARD({ dispatch }, boardId) {
      Axios.instance
        .delete("/api/board/delete", { params: { boardId } })
        .then(() => dispatch("FETCH_BOARD_LIST"))
        .catch(err => console.error(err));
    },

    SAVE_IN_BOARD({ dispatch }, { boardId, article }) {
      const data = {
        pubDate: new Date(article.pubDate) || new Date(),
        description: article.description,
        link: article.link,
        title: article.title,
        rssTitle: article.rssTitle,
        subscribeName: article.subscribeName
      };

      Axios.instance
        .post("/api/news/save", article, { params: { boardId } })
        .then(({ data }) => {
          dispatch("FETCH_BOARD_LIST");
        })
        .catch(err => console.error(err));
    },

    COPY_IN_BOARD({ dispatch }, { boardId, article }) {
      const data = {
        newsDate: new Date(article.newsDate) || new Date(),
        newsDescription: article.newsDescription,
        newsLink: article.newsLink,
        newsTitle: article.newsTitle,
        newsId: article.newsId,
        userNo: article.userNo
      };
      Axios.instance
        .post("/api/board/copy", data, { params: { boardId } })
        .then(({ data }) => {
          dispatch("FETCH_BOARD_LIST");
        })
        .catch(err => console.error(err));
    },

    DELETE_IN_BOARD({ dispatch }, newsId) {
      Axios.instance
        .delete("/api/news/delete", { params: { newsId } })
        .then(() => dispatch("FETCH_BOARD_LIST"))
        .catch(err => console.error(err));
    },

    FETCH_ARTICLE_IN_BOARD({ commit }, newsId) {
      Axios.instance
        .get("/api/news/find/id", { params: { newsId } })
        .then(({ data }) => {
          commit("SET_NEWS", data.data);
        })
        .catch(err => console.error(err))
        .finally(() => commit("SET_LOADING"));
    },

    FETCH_CATEGORY_LIST({ commit }) {
      Axios.instance
        .get("/api/category/find/count")
        .then(({ data }) => commit("SET_CATEGORY_LIST", data.data))
        .catch(err => console.error(err));
    },

    FETCH_SEARCH_CATEGORY({ commit }, categoryName) {
      Axios.instance
        .get("/api/rss/find/channel/categorylike", { params: { categoryName } })
        .then(({ data }) => commit("SET_RSS_LIST", data.data))
        .catch(err => console.error(err));
    },

    FETCH_SEARCH_RSS({ commit }, rssName) {
      Axios.instance
        .get("/api/rss/find/channel/rsslike", { params: { rssName } })
        .then(({ data }) => commit("SET_RSS_LIST", data.data))
        .catch(err => console.error(err));
    },

    FETCH_ARTICLE_DETAIL({ commit }, rssItem: Article) {
      return Axios.instance
        .post("/api/find/news/detail", rssItem)
        .then(({ data }) => {
          commit("SET_ARTICLE_DETAIL", data.data);
        })
        .catch(err => console.error(err))
        .finally(() => commit("SET_LOADING"));
    }
  }
};

export default module;
