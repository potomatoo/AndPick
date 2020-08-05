<template>
  <v-main>
    <v-layout>
      <v-flex v-if="isLoggedIn" offset-lg3 lg6>
        <router-view></router-view>
      </v-flex>
      <v-flex v-if="!isLoggedIn">
        <router-view></router-view>
      </v-flex>
    </v-layout>
  </v-main>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { mapGetters } from "vuex";

@Component({
  computed: { ...mapGetters(["isLoggedIn"]) }
})
export default class AppMain extends Vue {}
</script>
