import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "../views/main/a.vue";
import Today from "@/views/feeds/TodayFeedList.vue";
import Later from "@/views/feeds/ReadLaterList.vue";
import AddRss from "@/views/feeds/AddRss.vue";
import FeedPage from "@/views/feeds/FeedPage.vue";
import BoardArticleList from "@/views/feeds/BoardArticleList.vue";
import BoardArticleDetail from "@/views/feeds/BoardArticleDetail.vue";
import ArticleListInRss from "@/views/feeds/ArticleListInRss.vue";
import ArticleDetail from "@/views/feeds/ArticleDetail.vue";
import ArticleDetailInFeed from "@/views/feeds/ArticleDetailInFeed.vue";
import MypageMain from "@/views/pages/MypageMain.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/today",
    name: "Today",
    component: Today
  },
  {
    path: "/later",
    name: "Later",
    component: Later
  },
  {
    path: "/mypage",
    name: "MypageMain",
    component: MypageMain
  },
  {
    path: "/add",
    name: "AddRss",
    component: AddRss
  },
  {
    path: "/feed/:feedId",
    name: "Feed",
    component: FeedPage
  },
  {
    path: "/:feedName/subscription/:subscribeId",
    name: "ArticleListInRss",
    component: ArticleListInRss
  },
  {
    path: "/:feedName/subscription/:subscribeId/:articleId",
    name: "ArticleDetail",
    component: ArticleDetail
  },
  {
    path: "/feed/:feedId/article/:articleId",
    name: "ArticleDetailInFeed",
    component: ArticleDetailInFeed
  },
  {
    path: "/board/:boardId",
    name: "BoardArticleList",
    component: BoardArticleList
  },
  {
    path: "/board/:boardId/news/:newsId",
    name: "BoardArticleDetail",
    component: BoardArticleDetail
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
