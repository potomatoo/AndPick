<template>
  <div>
    <v-icon
      class="ml-3"
      style="color: black;"
      @click="TOGGLE_CREATEFOLDERMODAL()"
      >mdi-folder-plus</v-icon
    >
    <CreateFolderModal />
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
  @mypageModule.Mutation TOGGLE_CREATEFOLDERMODAL: any;
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
