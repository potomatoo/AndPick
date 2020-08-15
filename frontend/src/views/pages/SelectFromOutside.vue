<template>
  <div class="container">
    <h1>{{ scrapData }}</h1>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";

const mypageModule = namespace("mypageModule");

@Component
export default class SelectFromOutside extends Vue {
  @mypageModule.Action SAVE_SCRAPDATA: any;

  scrapData: string | null = "";

  created() {
    this.SAVE_SCRAPDATA(this.$route.params.scrapKey);
    console.log("앙2");
    this.scrapData = localStorage.getItem("scrapData");
  }
}
</script>
