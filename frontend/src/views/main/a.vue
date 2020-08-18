<template>
  <div class="main-page mt-15 mb-15">
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
            &PICK은 IT업계를 준비하는 사람들을 위한 웹 서비스입니다.<br />
            양질의 뉴스기사 및 기술블로그의 글을 실시간으로 제공합니다.<br />
            <br />
            나아가 다양한 웹 사이트에서 자료를 가져와 생각을 더하고 싶다면<br />
            구글 확장프로그램 "&PICKclipper"와 함께 사용해보세요!
          </div>
        </v-col>
        <v-col offset md="3" lg="3" xl="3">
          <div style="margin-left: 20px; margin-top: 30px">
            <span
              style="font-family: 'Spectral SC', serif; font-size: 140px; color: #5cb85c !important"
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
      <div>
        <div class="chart-wrapper"></div>
        <button onclick="replay()">Replay</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";

const feedModule = namespace("feedModule");

@Component
export default class A extends Vue {
  isLoggedIn = this.$store.getters.isLoggedIn;

  @Watch("isLoggedIn", { immediate: true })
  fetchData() {
    this.$store.dispatch("feedModule/FETCH_FEED_LIST");
    this.$store.dispatch("feedModule/FETCH_BOARD_LIST");
    this.$store.dispatch("mypageModule/FETCH_POSTDIR_LIST");
  }
}
</script>

<style scoped>
.main-description {
  color: rgba(0, 0, 0, 0.54) !important;
}
</style>
