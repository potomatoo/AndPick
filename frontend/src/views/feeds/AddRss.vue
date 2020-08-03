<template>
  <div class="mt-10">
    <v-container>
      <v-layout>
        <v-flex>
          <h1>Follow New Sources</h1>
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

    <v-container>
      <v-layout>
        <h4>Discover the best sources for any topic</h4>
      </v-layout>
      <v-text-field
        outlined
        label="Search by topic, website, or RSS link"
        type="text"
        clearable
        prepend-inner-icon="mdi-magnify"
      ></v-text-field>
      <v-layout justify-center>
        <p>
          The articles you want to read later will be here
        </p>
      </v-layout>
    </v-container>

    <v-container fluid>
      <!-- <v-row> -->
      <v-col v-for="rss in rssList" :key="rss.rssId">
        <v-card max-height="250px">
          <v-card-text>
            <div class="float-left">Word of the Day</div>

            <!-- ADD 버튼 메뉴 -->
            <v-menu offset-x :close-on-content-click="false" min-width="250px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  text
                  color="deep-purple accent-4"
                  v-bind="attrs"
                  v-on="on"
                  class="float-right"
                >
                  Add
                </v-btn>
              </template>
              <v-list>
                <v-list-item v-for="(feed, i) in feedList" :key="i">
                  <v-list-item-title>{{ feed.feedName }}</v-list-item-title>
                  <v-btn
                    v-if="checkSubscribe(feed.subscribeList, rss)"
                    class="ml-3"
                    outlined
                    color="success"
                    @click="addRss(feed.feedId, rss)"
                  >
                    <v-icon left>mdi-plus</v-icon> ADD
                  </v-btn>
                  <v-btn
                    v-else
                    class="ml-3"
                    outlined
                    color="error"
                    @click="addRss(feed.feedId, rss)"
                  >
                    <v-icon left>mdi-window-close</v-icon> REMOVE
                  </v-btn>
                </v-list-item>
              </v-list>
            </v-menu>

            <p class="display-1 text--primary mt-10">
              {{
                rss.rssName ||
                  ["동아경제", "노컷경제", "칸경제", "", "칸IT"][rss.rssId - 1]
              }}
            </p>
            <p>adjective</p>
            <div class="text--primary">
              well meaning and kindly.<br />
              "a benevolent smile"
            </div>
          </v-card-text>
        </v-card>
      </v-col>
      <!-- </v-row> -->
    </v-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Rss, SubscribeList } from "../../store/Feed.interface";

const feedModule = namespace("feedModule");

@Component
export default class AddRss extends Vue {
  @feedModule.State rssList!: [];
  @feedModule.State feedList!: [];
  @feedModule.Action FETCH_RSS: any;
  @feedModule.Action SUBSCRIBE_RSS: any;

  addRss(feedId: number, rss: Rss) {
    this.SUBSCRIBE_RSS({ feedId, rss });
  }

  checkSubscribe(subscribedList: SubscribeList[], rss: Rss) {
    return !(
      subscribedList.length &&
      subscribedList.some(el => el.rss.rssId === rss.rssId)
    );
  }

  created() {
    this.FETCH_RSS();
  }
}
</script>

<style></style>
