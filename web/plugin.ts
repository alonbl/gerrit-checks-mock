import '@gerritcodereview/typescript-api/gerrit';
import {ChecksMockProvider, ChecksMockProviderConfig} from './checks-mock';
import {PluginApi} from '@gerritcodereview/typescript-api/plugin';

window.Gerrit.install((plugin: PluginApi) => {
	async function init() : Promise<void> {
		let config: ChecksMockProviderConfig = await plugin.restApi()
			.get(`/config/server/${plugin.getPluginName()}~config`);
		console.info(`${plugin.getPluginName()} provider config ${JSON.stringify(config)}`);
		if (config.enable) {
			plugin.checks().register(new ChecksMockProvider(plugin, config), {
				fetchPollingIntervalSeconds: config.fetchPollingIntervalSeconds,
			});
		}
	}
	init();
});
