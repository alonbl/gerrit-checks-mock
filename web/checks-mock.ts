import {PluginApi} from '@gerritcodereview/typescript-api/plugin';
import {
	ChangeData,
	FetchResponse,
	ResponseCode,
} from '@gerritcodereview/typescript-api/checks';

export declare interface ChecksMockProviderConfig {
	enable: boolean;
	fetchPollingIntervalSeconds: number;
}

declare interface ChecksMockProjectConfig {
	enable: boolean;
}

export class ChecksMockProvider {
	plugin: PluginApi;
	config: ChecksMockProviderConfig;
	projectConfig: { [name: string]: ChecksMockProjectConfig } = {};

	private async _getProjectConfig(repo: string) : Promise<ChecksMockProjectConfig> {
		let projectConfig: ChecksMockProjectConfig = this.projectConfig[repo];

		if (projectConfig === undefined) {
			projectConfig = await this.plugin
				.restApi()
				.get(`/projects/${encodeURIComponent(repo)}/${this.plugin.getPluginName()}~config`);
			this.projectConfig[repo] = projectConfig;

			console.info(`${this.plugin.getPluginName()} project ${repo} config ${JSON.stringify(projectConfig)}`);
		}
		return projectConfig;
	}

	constructor(plugin: PluginApi, config: ChecksMockProviderConfig) {
		this.plugin = plugin;
		this.config = config;
	}

	/**
	 * Surfaces a warning if there are files with low coverage in the patchset.
	 *
	 * @param changeNum The change number of the patchset.
	 * @param patchNum The patchset number of the patchset.
	 * @return Returns an object representing the warnings.
	 *	On error, it logs the error and returns null/undefined.
	 */
	async fetch(change: ChangeData): Promise<FetchResponse> {
		try {
			const projectConfig = await this._getProjectConfig(change.repo);
			if (!projectConfig.enable) {
				return {
					responseCode: ResponseCode.OK,
				};
			}

			return await this.plugin
				.restApi()
				.get(`/changes/${change.changeInfo.id}/revisions/${change.patchsetNumber}/${this.plugin.getPluginName()}~checks_fetch`);
		} catch (error: unknown) {
			console.info(error);
			return {
				responseCode: ResponseCode.OK,
			};
		}
	}
}
