<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex v-if="feed">
          <div class="caption mb-3">피드</div>
          <h1 style="font-family: 'Do Hyeon', sans-serif;">
            {{ feed.feedName }}
          </h1>
        </v-flex>
        <v-flex class="text-right" align-self-end>
          <v-tooltip @click="fetchData" bottom open-delay="300" color="#EEEEEE">
            <template v-slot:activator="{ on, attrs }">
              <v-icon v-bind="attrs" v-on="on">
                mdi-replay mdi-flip-h
              </v-icon>
            </template>
            <span class="grey--text text--darken-1">새로고침</span>
          </v-tooltip>
        </v-flex>
      </v-layout>
    </v-container>
    <v-divider></v-divider>

    <!-- 구독한 채널이 없을 때 -->
    <v-container v-if="!articleList.length" class="text-center">
      <v-icon style="font-size: 180px" color="rgb(236, 193, 156)"
        >mdi-comment-plus-outline</v-icon
      >
      <h4
        v-if="feed"
        class="mt-10"
        style="font-family: 'Do Hyeon', sans-serif;"
      >
        "{{ feed.feedName }}"의 첫 번째 채널을 구독해주세요!
      </h4>

      <p class="text-center">
        채널을 구독하여 실시간으로 기사를 볼 수 있는 피드가 생성되었습니다.

        <v-btn small outlined color="rgb(30, 132, 127)" @click="addRss">
          <v-icon left>mdi-plus</v-icon>채널
        </v-btn>

        을 클릭하여 시작하세요.
      </p>
    </v-container>

    <!-- 구독한 채널이 있을 때 -->
    <v-container v-else>
      <v-list three-line max-width="700PX">
        <v-list-item-group>
          <v-list-item
            v-for="(article, idx) in articleList"
            :key="idx"
            @click="selectArticle(article, idx)"
            class="mb-3"
          >
            <v-row>
              <v-col sm="2">
                <img
                  v-if="article.imgsrc"
                  class="article-img"
                  :src="article.imgsrc"
                  onerror="this.src='http://i3b107.p.ssafy.io/img/logo-img.91ab7a7f.png'"
                  :alt="article.title"
                />
                <img
                  v-else
                  class="article-img"
                  src="@/assets/logo-img.png"
                  :alt="article.title"
                />
              </v-col>
              <v-col>
                <v-list-item-content class="pt-0">
                  <div class="h4">{{ article.title }}</div>
                  <div class="text-caption text--secondary">
                    {{ article.rssTitle }} /
                    {{ setDate(article.pubDate) }}
                  </div>
                  <v-list-item-subtitle class="mt-2">
                    {{ article.description }}
                  </v-list-item-subtitle>
                </v-list-item-content>
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
import { Article, FeedList } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class FeedPage extends Vue {
  @feedModule.State articleList!: Article[];
  @feedModule.State feed!: FeedList;
  @feedModule.State isLoading!: boolean;
  @feedModule.Mutation SET_ARTICLE_DETAIL: any;
  @feedModule.Mutation SET_LOADING_TRUE: any;
  @feedModule.Action FETCH_FEED!: any;
  @feedModule.Action FETCH_ARTICLE_DETAIL: any;

  @Watch("$route", { immediate: true })
  fetchData() {
    this.SET_LOADING_TRUE();
    this.FETCH_FEED(this.$route.params.feedId);
  }

  toArticleDetail(idx: number) {
    this.$router.push({
      name: "ArticleDetailInFeed",
      params: { articleId: idx.toString() }
    });
  }

  async selectArticle(article: Article, idx: number) {
    this.SET_LOADING_TRUE();
    this.SET_ARTICLE_DETAIL(article);
    await this.FETCH_ARTICLE_DETAIL(article);
    this.toArticleDetail(idx);
  }

  addRss() {
    this.$router.push({ name: "AddRss" });
  }

  setDate(date: string) {
    return date.slice(0, 10) + " " + date.slice(11, 16);
  }
}
</script>

<style scoped>
.article-img {
  width: 100%;
  margin-top: 12px;
  border: 2px soild;
  border-radius: 7px;
  height: 100px;
}
</style>
