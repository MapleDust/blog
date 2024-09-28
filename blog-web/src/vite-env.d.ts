/// <reference types="vite/client" />
// types/vue-wechat-title.d.ts
declare module 'vue-wechat-title' {
    const content: any;
    export default content;
}
// vite-env.d.ts
declare namespace NodeJS {
    interface ImportMetaEnv {
        // 你的环境变量声明
        readonly VITE_APP_TITLE: string;
        // ... 其他环境变量
    }
}

interface ImportMeta {
    readonly env: NodeJS.ImportMetaEnv;
}
