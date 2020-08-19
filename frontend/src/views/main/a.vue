<template>
  <div class="main-page mt-5 mb-15">
    <v-container>
      <v-row>
        <v-col xs="12" sm="12" md="7" lg="7" xl="7">
          <div style="font-family: 'Do Hyeon', sans-serif; font-size: 35px">
            <b>채널을 구독하고 나만의 생각을 정리하세요!</b>
          </div>

          <div
            class="main-description"
            style="font-weight: bold; margin-top: 50px"
          >
            &PICK은 나만의 스크랩을 위한 웹 서비스입니다.<br />
            양질의 뉴스기사 및 기술블로그의 글을 실시간으로 제공합니다.<br />
            <br />
            나아가 다양한 웹 사이트에서 자료를 가져와 생각을 더하고 싶다면<br />
            구글 확장프로그램 "&PICKClipper"와 함께 사용해보세요!
            <div class="ml-0 mt-4">
              <v-btn text small outlined @click="goExtension">바로가기</v-btn>
            </div>
          </div>
        </v-col>
        <v-col offset md="3" lg="3" xl="3">
          <div style="margin-left: 20px; margin-top: 30px">
            <span
              style="font-family: 'Spectral SC', serif; font-size: 140px; color: #1e847f !important"
              >&</span
            >
            <span style="font-family: 'Faster One', cursive; font-size: 100px"
              >PICK</span
            >
          </div>
        </v-col>
      </v-row>
    </v-container>

    <div>
      <v-divider></v-divider>

      <div class="d-flex justify-content-around">
        <div>
          <v-progress-circular
            :rotate="360"
            :size="150"
            :width="10"
            :value="value"
            color="#ecc19c"
          >
            {{ channel }}
          </v-progress-circular>

          <div class="d-flex justify-content-center">
            <b>전체 채널</b>
          </div>
        </div>

        <div>
          <v-progress-circular
            :rotate="-90"
            :size="150"
            :width="10"
            :value="value"
            color="#1e847f"
          >
            {{ saveNew }}
          </v-progress-circular>
          <div class="d-flex justify-content-center">
            <b>저장 기사</b>
          </div>
        </div>

        <div>
          <v-progress-circular
            :rotate="180"
            :size="150"
            :width="10"
            :value="value"
            color="#000000"
          >
            {{ user }}
          </v-progress-circular>
          <div class="d-flex justify-content-center">
            <b>사용자</b>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";

const feedModule = namespace("feedModule");
const mypageModule = namespace("mypageModule");

@Component
export default class A extends Vue {
  @mypageModule.State rssChannel!: number;
  @mypageModule.State saveNews!: number;
  @mypageModule.State users!: number;
  @mypageModule.Mutation SET_MAINPAGE_COUNT: any;

  isLoggedIn = this.$store.getters.isLoggedIn;

  interval = {};
  value = 0;

  channel = 0;
  saveNew = 0;
  user = 0;

  goExtension() {
    window.open(
      "https://chrome.google.com/webstore/detail/pickclipper/bggenjcdpkngebimckblkeeiciegaenk?hl=ko&",
      "_blank"
    );
  }

  @Watch("isLoggedIn", { immediate: true })
  fetchData() {
    this.$store.dispatch("feedModule/FETCH_FEED_LIST");
    this.$store.dispatch("feedModule/FETCH_BOARD_LIST");
    this.$store.dispatch("mypageModule/FETCH_POSTDIR_LIST");
  }

  @Watch("$route", { immediate: true })
  setCount() {
    this.SET_MAINPAGE_COUNT();
  }

  mounted() {
    this.interval = setInterval(() => {
      if (this.value < 100) {
        this.value += 10;
        return;
      }
      if (this.value >= 100) {
        this.channel = this.rssChannel;
        this.saveNew = this.saveNews;
        this.user = this.users;
      }
    }, 100);
    return;
  }
}
</script>

<style scoped>
.main-description {
  color: rgba(0, 0, 0, 0.54) !important;
}
.v-progress-circular {
  margin: 1rem;
}
</style>
