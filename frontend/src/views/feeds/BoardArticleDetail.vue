<template>
  <div class="mt-10" v-if="news">
    <div :class="{ left: onEdit }">
      <v-container>
        <!-- <v-layout> -->
        <h3>{{ news.newsTitle }}</h3>
        <div class="subtitle-1 text--secondary">{{ news.newsDate }}</div>
        <div class="text-right">
          <add-board-menu />
          <add-scrap-menu @onPost="onPost" @onNewPost="onNewPost" />
        </div>
      </v-container>

      <v-divider></v-divider>

      <v-container class="text-center">
        <p :v-html="news.newsDescription" :class="{ desc: !onEdit }">
          {{ news.newsDescription }}
        </p>
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
  @feedModule.Action FETCH_ARTICLE_IN_BOARD: any;

  onEdit = false;

  openPage(link: string) {
    window.open(link);
  }

  fetchData() {
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
