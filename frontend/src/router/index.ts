import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "@/views/main/a.vue";
import Cover from "@/views/Cover.vue";
import Today from "@/views/feeds/TodayFeedList.vue";
import FolderMain from "@/views/pages/FolderMain.vue";

import EditArticle from "@/views/pages/EditArticle.vue"
import Later from "@/views/feeds/ReadLaterList.vue";
import AddRss from "@/views/feeds/AddRss.vue";
import FeedPage from "@/views/feeds/FeedPage.vue";
import BoardList from "@/views/feeds/BoardList.vue";
import ArticleListInRss from "@/views/feeds/ArticleListInRss.vue";
import ArticleDetail from "@/views/feeds/ArticleDetail.vue";


import SignupView from "@/views/accounts/SignupView.vue";
import LoginView from "@/views/accounts/LoginView.vue";
import LogoutView from "@/views/accounts/LogoutView.vue";
import UpdateUserView from "@/views/accounts/UpdateUserView.vue";
import DeleteUserView from "@/views/accounts/DeleteUserView.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    component: Home,
    beforeEnter(from, to, next) {
      console.log(from, to);
      if (!window.sessionStorage.getItem("jwt-token")) {
        next("/cover");
        console.log(!window.sessionStorage.getItem("jwt-token"));
      } else {
        next();
      }
    },
  },
  {
    path: "/cover",
    name: "Cover",
    component: Cover,
  },
  {
    path: "/accounts/signup",
    name: "Signup",
    component: SignupView,
  },
  {
    path: "/accounts/login",
    name: "Login",
    component: LoginView,
  },
  {
    path: "/accounts/logout",
    name: "Logout",
    component: LogoutView,
  },
  {
    path: "/accounts/delete",
    name: "DeleteUser",
    component: DeleteUserView,
  },
  {
    path: "/accounts/update",
    name: "UpdateUser",
    component: UpdateUserView,
  },
  {
    path: "/today",
    name: "Today",
    component: Today,
  },
  {
    path: "/later",
    name: "Later",
    component: Later
  },
  {
    path: "/mypage/:pageName",
    name: "FolderMain",
    component: FolderMain,
  },

  {
    path: "/mypage/:pageName/post",
    name: "EditArticle",
    component: EditArticle,
  },

  {
    path: "/add",
    name: "AddRss",
    component: AddRss
  },
  {
    path: "/feeds/:feedName",
    name: "Feed",
    component: FeedPage
  },
  {
    path: "/:feedName/subscription/:subscribeId",
    name: "ArticleListInRss",
    component: ArticleListInRss
  },
  {
    path: "/:feedname/subscription/:rssId/:articleId",
    name: "ArticleDetail",
    component: ArticleDetail
  },
  {
    path: "/boards/:boardName",
    name: "BoardList",
    component: BoardList
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

// router.beforeEach(function(to, from, next) {
//   if (!window.sessionStorage.getItem("jwt-token")) {
//     next("/cover");
//     next(false);
//   } else {
//     next();
//   }
// });

export default router;
