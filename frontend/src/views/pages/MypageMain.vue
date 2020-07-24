<template>
  <folder-main />
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import CreateFolder from "@/components/pages/CreateFolder.vue";
import FolderMain from "@/components/pages/FolderMain.vue";

@Component({
  components: {
    CreateFolder,
    FolderMain
  }
})
export default class MypageMain extends Vue {}
</script>

<style></style>
