<template>
  <div>
    <v-icon class="ml-3" style="color: black;" @click="isActive = !isActive"
      >mdi-folder-plus</v-icon
    >
    <CreateFolderModal :isActive="isActive" />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
import CreateFolderModal from "./CreateFolderModal.vue";

const mypageModule = namespace("mypageModule");

@Component({
  components: {
    CreateFolderModal
  }
})
export default class CreateFolder extends Vue {
  isActive = false;
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
