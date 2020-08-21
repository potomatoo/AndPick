<template>
  <v-main class="v-main">
    <v-layout>
      <v-flex v-if="isLoggedIn" offset-xs1 xs10>
        <router-view></router-view>
      </v-flex>
      <v-flex v-if="!isLoggedIn">
        <router-view></router-view>
      </v-flex>
    </v-layout>
    <transition name="slide-fade">
      <v-btn
        v-if="showbtn"
        @click="scrollToTop"
        class="button-bottom"
        dark
        fab
        small
        color="#ecc19c"
      >
        <v-icon dark>mdi-menu-up</v-icon>
      </v-btn>
    </transition>
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
    }
  }
})
export default class AppMain extends Vue {
  showbtn = false;
  handleScroll() {
    if (document.documentElement.scrollTop > 300) {
      this.showbtn = true;
    } else {
      this.showbtn = false;
    }
  }
  created() {
    window.addEventListener("scroll", this.handleScroll);
  }
}
</script>
<style>
.v-main {
  padding-bottom: 0px !important;
}
.button-bottom {
  position: fixed;
  right: 3vw;
  bottom: 12vh;
  text-decoration: none;
  color: inherit;
  border: 0;
  outline: none !important;
}
.slide-fade-enter-active {
  transition: all 1s ease;
}
.slide-fade-leave-active {
  transition: all 1.5s ease;
}
.slide-fade-enter,
.slide-fade-leave-to {
  transform: translateY(20px);
  opacity: 0;
}
</style>
