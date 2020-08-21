<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex v-if="board">
          <div class="caption mb-3">보드</div>
          <h1 style="font-family: 'Do Hyeon', sans-serif;">
            {{ board.boardName }}
          </h1>
        </v-flex>
      </v-layout>
    </v-container>
    <v-divider></v-divider>

    <!-- 기사 없을때 -->
    <v-container v-if="board && !board.newsList.length" class="text-center">
      <v-icon style="font-size: 180px" color="rgb(236, 193, 156)"
        >mdi-comment-plus-outline</v-icon
      >
      <h4 class="mt-10" style="font-family: 'Do Hyeon', sans-serif;">
        "{{ board.boardName }}"의 첫 번째 기사를 저장해주세요!
      </h4>

      <p class="text-center">
        뉴스를 저장할 보드가 생성되었습니다. 구독한 채널에서 소장하고 싶은
        기사를 자유롭게 저장하세요!
      </p>
    </v-container>

    <!-- 기사 있을때 -->
    <v-container v-else-if="board && board.newsList.length">
      <v-list three-line max-width="700px">
        <v-list-item-group>
          <v-list-item
            v-for="news in board.newsList"
            :key="news.newsId"
            @click="toArticleDetail(board.boardId, news.newsId)"
            class="mb-3"
          >
            <v-row>
              <v-col sm="2">
                <img
                  v-if="news.newsImg"
                  class="news-img"
                  :src="news.newsImg"
                  onerror="this.src='http://i3b107.p.ssafy.io/img/logo-img.91ab7a7f.png'"
                  :alt="news.title"
                />
                <img
                  v-else
                  class="news-img"
                  src="@/assets/logo-img.png"
                  :alt="news.title"
                />
              </v-col>
              <v-col>
                <!-- <v-list-item-content class="pt-0"> -->
                <v-list-item-content class="pt-0">
                  <div class="h4">{{ news.newsTitle }}</div>
                  <div class="text-caption text--secondary">
                    {{ setDate(news.newsDate) }}
                  </div>
                  <!-- <div class="sumtitle-1 text--secondary">
                    {{ article.rssTitle }}
                  </div> -->
                  <v-list-item-subtitle class="mt-2">
                    {{ news.newsDescription }}
                  </v-list-item-subtitle>
                </v-list-item-content>
                <!-- </v-list-item-content> -->
              </v-col>
            </v-row>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-container>

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
import { Board } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class BoardArticleList extends Vue {
  @feedModule.State board!: Board;
  @feedModule.State isLoading!: boolean;
  @feedModule.Mutation SET_LOADING_TRUE: any;
  @feedModule.Action FETCH_ARTICLE_LIST_IN_BOARD: any;

  @Watch("$route", { immediate: true })
  fetchData() {
    this.SET_LOADING_TRUE();
    this.FETCH_ARTICLE_LIST_IN_BOARD(this.$route.params.boardId);
  }

  toArticleDetail(boardId: number, newsId: number) {
    this.$router.push({
      name: "BoardArticleDetail",
      params: { boardId: boardId.toString(), newsId: newsId.toString() }
    });
  }

  setDate(date: string) {
    return date.slice(0, 10) + " " + date.slice(11, 16);
  }
}
</script>

<style scoped>
.news-img {
  width: 100%;
  margin-top: 12px;
  border: 2px soild;
  border-radius: 7px;
  height: 100px;
}
</style>
