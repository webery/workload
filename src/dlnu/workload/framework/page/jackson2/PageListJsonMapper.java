package dlnu.workload.framework.page.jackson2;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import dlnu.workload.framework.page.domain.JsonPage;

/**
 * @author miemiedev
 */
public class PageListJsonMapper extends ObjectMapper {

	public PageListJsonMapper() {
		//super();//weber
		SimpleModule module = new SimpleModule("PageListJSONModule",
				new Version(1, 0, 0, null, null, null));
		module.addSerializer(JsonPage.class, new PageListJsonSerializer(this));
		registerModule(module);
	}
}
