<template>
  <div>
    <h1>Signup</h1>
    <div class="form-group">
      <label for="userId">userID:</label>
      <input
        v-model="signupData.userId"
        class="form-control"
        id="userId"
        type="text"
      />
    </div>
    <div class="form-group">
      <label for="userName">userName:</label>
      <input
        v-model="signupData.userName"
        class="form-control"
        id="userName"
        type="text"
      />
    </div>
    <div class="form-group">
      <label for="userPassword">password:</label>
      <input
        v-model="signupData.userPassword"
        class="form-control"
        id="userPassword"
        type="password"
      />
    </div>
    <div>
      <input v-model="signupData.userType" id="userType" type="hidden" />
    </div>
    <div>
      <button @click="signup(signupData)">Signup</button>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import { mapActions } from "vuex";

interface SignupData {
  userId: string | null;
  userName: string | null;
  userPassword: string | null;
  userType: number;
}

@Component({
  methods: {
    ...mapActions(["signup"]),
  },
})
export default class SignupView extends Vue {
  signupData: SignupData = {
    userId: null,
    userName: null,
    userPassword: null,
    userType: 0,
  };
}
</script>

<style></style>
