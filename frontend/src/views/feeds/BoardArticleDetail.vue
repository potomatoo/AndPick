<template>
  <div class="mt-10" v-if="news">
    <div :class="{ left: onEdit }">
      <v-container>
        <div class="text-left">
          <add-board-menu />
          <add-scrap-menu @onPost="onPost" @onNewPost="onNewPost" />
        </div>
        <v-divider class="mt-2"></v-divider>
        <h3 class="mt-10 mb-0" style="font-family: 'Do Hyeon', sans-serif;">
          {{ news.newsTitle }}
        </h3>
        <div class="text-caption text--secondary">
          {{ setDate(news.newsDate) }}
        </div>
      </v-container>

      <v-container>
        <div
          class="article-desc"
          v-html="news.newsDescription"
          :class="{ desc: !onEdit }"
        >
          {{ news.newsDescription }}
        </div>
        <div :class="{ desc: !onEdit }">
          <v-btn
            color="grey"
            outlined
            dark
            block
            tile
            @click="openPage(news.newsLink)"
            >VISIT WEBSITE</v-btn
          >
        </div>
      </v-container>
    </div>
    <div :class="{ vl: onEdit }"></div>
    <div :class="{ right: onEdit }">
      <router-view @save="saveEdit" />
    </div>

    <v-overlay :value="isLoading">
      <v-progress-circular
        indeterminate
        size="80"
        width="10"
        color="rgb(236, 193, 156)"
      ></v-progress-circular>
    </v-overlay>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { News } from "../../store/Feed.interface";

import AddBoardMenu from "@/components/feeds/AddBoardMenu.vue";
import AddScrapMenu from "@/components/feeds/AddScrapMenu.vue";

const feedModule = namespace("feedModule");

@Component({
  components: {
    AddBoardMenu,
    AddScrapMenu
  }
})
export default class BoardArticleDetail extends Vue {
  @feedModule.State news!: News;
  @feedModule.State isLoading!: boolean;
  @feedModule.Mutation SET_LOADING: any;
  @feedModule.Action FETCH_ARTICLE_IN_BOARD: any;

  onEdit = false;

  openPage(link: string) {
    window.open(link);
  }

  fetchData() {
    this.SET_LOADING();
    this.FETCH_ARTICLE_IN_BOARD(this.$route.params.newsId);
  }

  created() {
    this.fetchData();
  }

  saveEdit() {
    this.$router.replace({
      name: "BoardArticleDetail",
      params: {
        boardId: this.$route.params.boardId,
        newsId: this.$route.params.newsId
      }
    });
    this.onEdit = false;
  }

  @Watch("$route", { immediate: true })
  checkRoute() {
    if (this.$route.name === "BoardArticleDetail") {
      this.onEdit = false;
    } else if (
      this.$route.name === "NewScrapInBoard" ||
      this.$route.name === "EditScrapInBoard"
    ) {
      this.onEdit = true;
    }
  }

  onPost(postId: number) {
    this.$router.push({
      name: "EditScrapInBoard",
      params: { postId: postId.toString() }
    });
  }

  onNewPost(postDirId: number) {
    this.$router.push({
      name: "NewScrapInBoard",
      params: { postDirId: postDirId.toString() }
    });
  }

  setDate(date: string) {
    return date.slice(0, 10) + " " + date.slice(11, 16);
  }

  // @Watch("$route")
  setStyle() {
    const article = document.querySelector(".article-desc");
    const images = article?.querySelectorAll("img");
    const videos = article?.querySelectorAll("video");
    const iframe = article?.querySelectorAll("iframe");
    const pTag = article?.querySelectorAll("p");
    if (images?.length) {
      images.forEach(el => {
        el.setAttribute("style", "width: 100%");
      });
    }
    if (videos?.length) {
      videos.forEach(el => el.setAttribute("style", "width: 100%"));
    }
    if (iframe?.length) {
      iframe.forEach(el => el.setAttribute("style", "width: 100%"));
    }
    if (pTag?.length) {
      pTag.forEach(el => el.setAttribute("style", "margin: 24px 0px"));
    }
  }

  updated() {
    this.setStyle();
  }
}
</script>

<style scoped>
.left {
  width: 50%;
  float: left;
  padding-right: 40px;
}

.right {
  width: 50%;
  float: right;
  padding-left: 40px;
}

.vl {
  border-left: 4px solid lightgrey;
  height: 100%;
  position: absolute;
  left: 50%;
  margin-left: -2px;
}

.desc {
  margin: 30px 150px 40px 150px;
}
</style>
