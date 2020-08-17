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
        <!-- <v-flex class="text-right">
          <v-icon @click="console.log('hi')">mdi-check</v-icon>
          <v-icon class="ml-3" @click="console.log('hi')"
            >mdi-replay mdi-flip-h</v-icon
          >
        </v-flex> -->
      </v-layout>
    </v-container>
    <v-divider></v-divider>

    <!-- 기사 없을때 -->
    <v-container v-if="board && !board.newsList.length" class="text-center">
      <v-icon style="font-size: 180px">mdi-comment-plus-outline</v-icon>
      <h4 class="mt-10" style="font-family: 'Do Hyeon', sans-serif;">
        "{{ board.boardName }}"의 첫 번째 기사를 저장해주세요!
      </h4>

      <p class="text-center">
        뉴스를 저장할 보드가 생성되었습니다. 구독한 채널에서 소장하고 싶은
        기사를 자유롭게 저장하세요!
      </p>
    </v-container>

    <!-- 기사 있을때 -->
    <v-container
      v-else-if="board && board.newsList.length"
      class="offset-lg3 lg6"
    >
      <v-list three-line>
        <v-list-item-group>
          <v-list-item v-for="news in board.newsList" :key="news.newsId">
            <v-list-item-content>
              <router-link
                class="router-link"
                :to="{
                  name: 'BoardArticleDetail',
                  params: { boardId: board.boardId, newsId: news.newsId }
                }"
              >
                <v-list-item-content class="mt-3">
                  <div class="h4">
                    {{ news.newsTitle }}
                  </div>
                  <div class="sumtitle-1 text--secondary">
                    {{ news.newsDate }}
                  </div>
                  <!-- <div class="sumtitle-1 text--secondary">
                    {{ article.rssTitle }}
                  </div> -->
                  <v-list-item-subtitle>
                    {{ news.newsDescription }}
                  </v-list-item-subtitle>
                </v-list-item-content>
              </router-link>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-container>
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
  @feedModule.Action FETCH_ARTICLE_LIST_IN_BOARD: any;

  @Watch("$route", { immediate: true })
  fetchData() {
    this.FETCH_ARTICLE_LIST_IN_BOARD(this.$route.params.boardId);
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
