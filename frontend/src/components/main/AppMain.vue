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
    <v-btn
      v-if="isLoggedIn"
      @click="scrollToTop"
      class="button-bottom"
      dark
      fab
      small
      color="success"
    >
      <v-icon dark>mdi-menu-up</v-icon>
    </v-btn>
  </v-main>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { mapGetters } from "vuex";

@Component({
  computed: { ...mapGetters(["isLoggedIn"]) },
  methods: {
    scrollToTop: function() {
      scroll(0, 0);
    },
  },
})
export default class AppMain extends Vue {}
</script>
<style>
.button-bottom {
  position: fixed;
  right: 7vw;
  bottom: 20vh;
}
</style>
