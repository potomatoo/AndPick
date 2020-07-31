<template>
  <div>
    <v-list>
      <v-subheader>
        Mypage
        <create-folder />
      </v-subheader>

      <v-list-item v-for="(item, i) in MyFolderList" :key="i" @click="click">
        <v-list-item-content>
          <router-link :to="{ name: 'MypageMain' }" class="router-link">
            <v-list-item-title v-text="item.title"></v-list-item-title>
          </router-link>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import CreateFolder from "@/components/pages/CreateFolder.vue";

import { mapState } from "vuex";

@Component({
  components: {
    CreateFolder
  },
  computed: mapState("mypageModule", ["MyFolderList"])
})
export default class SidebarMypage extends Vue {
  click() {
    console.log("click");
  }
}
</script>

<style scoped>
.router-link {
  text-decoration: none;
  color: inherit;
}
</style>
