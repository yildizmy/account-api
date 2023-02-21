const CracoLessPlugin = require('craco-less');

module.exports = {
    plugins: [
        {
        plugin: CracoLessPlugin,
        options: {
            lessLoaderOptions: {
            lessOptions: {
                modifyVars: { '@primary-color': '#0070AE' },
                javascriptEnabled: true,
            },
            },
        },
        },
    ],
};