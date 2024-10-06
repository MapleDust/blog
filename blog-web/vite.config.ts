import {defineConfig} from 'vite'

import obfuscator from 'rollup-plugin-obfuscator';
import viteCompression from 'vite-plugin-compression';
import imagemin from 'vite-plugin-imagemin';
import vue from '@vitejs/plugin-vue'
import {createHtmlPlugin} from 'vite-plugin-html';
// element-plus按需引入
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
        viteCompression({
            verbose: true,
            algorithm: 'gzip',
            ext: '.gz',
        }),
        imagemin({
            optipng: {
                optimizationLevel: 7,
            },
            gifsicle: {
                interlaced: false,
            },
            mozjpeg: {
                quality: 75,
            },
            svgo: {
                plugins: [{removeViewBox: true}, {cleanupIDs: true}],//cleanupIDs: false
            },
        }),
        obfuscator({
            global: true,
            options: {
                compact: true,
                controlFlowFlattening: true,
                deadCodeInjection: true,
                debugProtection: true,
                debugProtectionInterval: 0,
                disableConsoleOutput: false,
                identifierNamesGenerator: "mangled",
                log: false,
                numbersToExpressions: false,
                renameGlobals: true,//false
                selfDefending: true,//false
                simplify: true,
                splitStrings: true,//false
                stringArray: true,
                stringArrayCallsTransform: false,
                stringArrayCallsTransformThreshold: 0.5,
                stringArrayEncoding: [],
                stringArrayIndexShift: true,
                stringArrayRotate: true,
                stringArrayShuffle: true,
                stringArrayWrappersCount: 1,
                stringArrayWrappersChainedCalls: true,
                stringArrayWrappersParametersMaxCount: 2,
                stringArrayWrappersType: "variable",
                stringArrayThreshold: 0.75,
                unicodeEscapeSequence: false,
            }
        }),
        createHtmlPlugin({
            minify: true
        }),
        AutoImport({
            resolvers: [ElementPlusResolver()],
        }),
        Components({
            resolvers: [ElementPlusResolver()],
        }),
    ],
    build: {
        minify: 'terser', // 或者 'terser'，根据需要选择
        terserOptions: {
            compress: {
                drop_console: true,
                drop_debugger: true,
                pure_funcs: ['console.log', 'console.info', 'console.error'],
                ecma: 2020
            },
            format: {
                comments: false, // 确保不保留任何注释
                //beautify: false, // 不美化输出，即去除换行和空格
            },
        },
    },
})
