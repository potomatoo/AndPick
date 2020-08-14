<template>
  <div class="container">{{ scrapKey }}</div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";

const mypageModule = namespace("mypageModule");

@Component
export default class SelectFromOutside extends Vue {
  @mypageModule.Action SAVE_SCRAPDATA: any;

  created() {
    const scrapKey = localStorage.getItem("scrapKey");
    this.SAVE_SCRAPDATA(scrapKey);
  }
}
</script>
