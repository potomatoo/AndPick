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

    <!-- 기사 리스트 -->
    <v-container v-if="!articleList.length" class="text-center">
      <v-icon style="font-size: 180px">mdi-comment-plus-outline</v-icon>
      <h4 class="mt-10" style="font-family: 'Do Hyeon', sans-serif;">
        "{{ feed.feedName }}"의 첫 번째 채널을 구독해주세요!
      </h4>

      <p class="text-center">
        채널을 구독하여 실시간으로 기사를 볼 수 있는 피드가 생성되었습니다.

        <v-btn small outlined color="secondary" @click="addRss">
          <v-icon left>mdi-plus</v-icon>채널
        </v-btn>

        을 클릭하여 시작하세요.
      </p>
    </v-container>

    <v-container v-else class="offset-lg3 lg6">
      <v-list three-line>
        <v-list-item-group>
          <v-list-item v-for="(article, idx) in articleList" :key="idx">
            <v-list-item-content @click="selectArticle(article)">
              <router-link
                class="router-link"
                :to="{
                  name: 'ArticleDetailInFeed',
                  params: { articleId: idx }
                }"
              >
                <v-list-item-content class="mt-3">
                  <div class="h4">
                    {{ article.title }}
                  </div>
                  <div class="sumtitle-1 text--secondary">
                    {{ article.pubDate }}
                  </div>
                  <div class="sumtitle-1 text--secondary">
                    {{ article.rssTitle }}
                  </div>
                  <v-list-item-subtitle>
                    {{ article.description }}
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
import { Article, FeedList } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class FeedPage extends Vue {
  @feedModule.State articleList!: Article[];
  @feedModule.State feedTitle!: string;
  @feedModule.State feed!: FeedList;
  @feedModule.Mutation SELECT_ARTICLE: any;
  @feedModule.Action FETCH_FEED: any;
  @feedModule.Action FETCH_ARTICLE_LIST_IN_FEED!: any;

  @Watch("$route", { immediate: true })
  fetchData() {
    this.FETCH_ARTICLE_LIST_IN_FEED(this.$route.params.feedId);
  }

  selectArticle(article: Article) {
    this.SELECT_ARTICLE(article);
  }

  addRss() {
    this.$router.push({ name: "AddRss" });
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
