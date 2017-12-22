package me.wcy.spring.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.wcy.spring.app.common.Response;
import me.wcy.spring.app.common.HttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzwangchenyan on 2017/10/19.
 */
@RestController
public class ASRController {
    private static final Logger LOGGER = LogManager.getLogger(ASRController.class);

    @RequestMapping("/api/asr")
    public Response callback(
            @RequestBody String result
    ) {
        JSONObject object = JSON.parseObject(result);
        int code = object.getIntValue("ret_code");
        if (code == 1) {
            String sn = object.getString("sn");
            String url = snMap.get(sn);
            double duration = object.getDoubleValue("audio_duration");
            String text = object.getJSONArray("result").get(0).toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("url", url);
            jsonObject.put("duration", duration);
            jsonObject.put("text", text);

            LOGGER.info(jsonObject.toJSONString());
        }
        return new Response("");
    }

    private static final String source = "1.考拉 域名：kaolakefu appid：8aaf07085c346c5a015c618d9e860d24\n" +
            "https://nos.netease.com/ysf/857317f55e23b641855119943ed42889.wav\n" +
            "https://nos.netease.com/ysf/72987c2d29b7c228f68dfe47e9e749de.wav\n" +
            "https://nos.netease.com/ysf/ff5ff11d4fecaa6ea73b7db4e8a4b9cc.wav\n" +
            "https://nos.netease.com/ysf/1444a829ed4dcd3754f573b4440f3ffd.wav\n" +
            "https://nos.netease.com/ysf/31495fc20a6d4a7cac85e2fd0a529a8b.wav\n" +
            "https://nos.netease.com/ysf/7c9590804204cc23160f6f5ff05a1800.wav\n" +
            "https://nos.netease.com/ysf/7619c9362ba0792a9d5345faa9620e22.wav\n" +
            "https://nos.netease.com/ysf/0100e0074aec0c79001c86201f66331d.wav\n" +
            "https://nos.netease.com/ysf/d116bc0c00a60348e093b63c6a62b10d.wav\n" +
            "https://nos.netease.com/ysf/41db3b1949aa269470b0fbfe66615f90.wav\n" +
            "https://nos.netease.com/ysf/a141f4735711ff276563719d8aff9cac.wav\n" +
            "https://nos.netease.com/ysf/4b6a2c5ca65128bdc1f0660e785df202.wav\n" +
            "https://nos.netease.com/ysf/bc3f48938dad41f18d6f56caa3869e30.wav\n" +
            "https://nos.netease.com/ysf/a36b3b3076c6d17cad65c9999cc3d225.wav\n" +
            "https://nos.netease.com/ysf/1dd240d86b4143777ed53d19b9114e94.wav\n" +
            "https://nos.netease.com/ysf/2c16ececc87bdba14967d4e204ee92bc.wav\n" +
            "https://nos.netease.com/ysf/d49a2b021a9e12da71a27ea737061b92.wav\n" +
            "https://nos.netease.com/ysf/45741fefd8f41d552c486e98707540b5.wav\n" +
            "https://nos.netease.com/ysf/98043891a1f9c98373e03c72f7b64e1e.wav\n" +
            "https://nos.netease.com/ysf/c42e5be48941b44e81a1e5f6f48cd70d.wav\n" +
            "2.yoyo跑腿 域名：uupt appid：c9d0dcc22951418ea33359781057f6bb\n" +
            "https://nos.netease.com/ysf/923d23c679509c853e3a63a11f0e1cf1.wav\n" +
            "https://nos.netease.com/ysf/a366cb133e2f66ed9047a59a2863a489.wav\n" +
            "https://nos.netease.com/ysf/9ba3d45c4c15e86b7fb8986b0d5a9548.wav\n" +
            "https://nos.netease.com/ysf/607328b4bb8d75c26131929a1c91562d.wav\n" +
            "https://nos.netease.com/ysf/8ac396c00588325607f4027a2570a9c3.wav\n" +
            "https://nos.netease.com/ysf/f99e726dbf91359e039cd24f804c8b8f.wav\n" +
            "https://nos.netease.com/ysf/fbbe6ef920311a6b3a742cc2f3505ce8.wav\n" +
            "https://nos.netease.com/ysf/8c20c7080152dbc2f05d097d828d34ff.wav\n" +
            "https://nos.netease.com/ysf/2f1aee44ac5e1c362a0ebe6039d0528f.wav\n" +
            "https://nos.netease.com/ysf/6dc0f7c81d44fbc9f7e43bf71f4f71d8.wav\n" +
            "https://nos.netease.com/ysf/7c2951ace3c4b49018faad7c8da2b243.wav\n" +
            "https://nos.netease.com/ysf/0786d69a996f91eaeacb4244625f82eb.wav\n" +
            "https://nos.netease.com/ysf/34337c8d47a4dd427cc5d8318a84b21d.wav\n" +
            "https://nos.netease.com/ysf/b691ba6651b403b527952aac1a17f7e1.wav\n" +
            "https://nos.netease.com/ysf/fee3637d8e80606d71c9eb3217f9a9f7.wav\n" +
            "https://nos.netease.com/ysf/e98e551a756db2ac05a0136336ad6996.wav\n" +
            "https://nos.netease.com/ysf/1026e87cb4f9b9971d1821cbfcd5ee09.wav\n" +
            "https://nos.netease.com/ysf/ee9d332d1b45b541fde9ded3d029f007.wav\n" +
            "https://nos.netease.com/ysf/4e203d70004b783beb38f525e9d798ba.wav\n" +
            "https://nos.netease.com/ysf/ad39a523a89c35ad67a36a06a86e7f7f.wav\n" +
            "3.好药师 域名：ehaoyao appid:8a216da85d158d1b015d2c2eab98087e\n" +
            "https://nos.netease.com/ysf/b52b4a66bef95e57f2f50c7803d6ca7d.wav\n" +
            "https://nos.netease.com/ysf/ecb799d3383aabd6dc5d0d16c7f06471.wav\n" +
            "https://nos.netease.com/ysf/f91841bc56490c7fc8935bd7d0ea65ad.wav\n" +
            "https://nos.netease.com/ysf/1088ba0b4f078a1dca73846e8971721f.wav\n" +
            "https://nos.netease.com/ysf/53db9ebeb6205577509c47471823f208.wav\n" +
            "https://nos.netease.com/ysf/bb9c98682a9996aa6d07237cdc4c3c1d.wav\n" +
            "https://nos.netease.com/ysf/67b0706b3c5700d1d0e5cb145da58d07.wav\n" +
            "https://nos.netease.com/ysf/7284f24befbef4a303196fe331c3e0f2.wav\n" +
            "https://nos.netease.com/ysf/22a2d3ca71b69e3651c12c8adb77c46e.wav\n" +
            "https://nos.netease.com/ysf/c4551c5aa35562b73e55193b1a798bac.wav\n" +
            "https://nos.netease.com/ysf/ce3a93479656232986d74966cbcbabb9.wav\n" +
            "https://nos.netease.com/ysf/d9fd3745b7cf00d71b4222a78e36f4a2.wav\n" +
            "https://nos.netease.com/ysf/0209aab11dc3b2bba39404e8284f7d21.wav\n" +
            "https://nos.netease.com/ysf/41c53924c4eab2c63141327851876baa.wav\n" +
            "https://nos.netease.com/ysf/41db78ee84e1cd08127a4660228c7371.wav\n" +
            "https://nos.netease.com/ysf/2b8198ce6ea5f365d52383f08e20a3c7.wav\n" +
            "https://nos.netease.com/ysf/81690e18a140b00976725015198094a8.wav\n" +
            "https://nos.netease.com/ysf/a6f15eaa7fa4c9c87aa614606fa4bd98.wav\n" +
            "https://nos.netease.com/ysf/3e8d54ee6660969bb10f4bf7129dff1e.wav\n" +
            "https://nos.netease.com/ysf/880bc57f412dcd572abfacefb516e7d0.wav\n" +
            "4.神州鹰 域名：ztjy appid：8aaf07085af9176d015affb5494b023b\n" +
            "https://nos.netease.com/ysf/82797290449b189d5b5d6d2ab3a6f88f.wav\n" +
            "https://nos.netease.com/ysf/c465da4727f88ebc033a1d41d6a23e95.wav\n" +
            "https://nos.netease.com/ysf/149ec4014048c3cc25e018cc879d50bd.wav\n" +
            "https://nos.netease.com/ysf/ab38a148ac12c3cd5e6f5077c517e543.wav\n" +
            "https://nos.netease.com/ysf/55db3b5adaf6f6c1ec6a2c800a2372fc.wav\n" +
            "https://nos.netease.com/ysf/39d562d5d079ba2670e036e62d41b937.wav\n" +
            "https://nos.netease.com/ysf/9841f512ae64673b805e9f0907c6cf5d.wav\n" +
            "https://nos.netease.com/ysf/6b77268f269dc2c7f201af7b1248069f.wav\n" +
            "https://nos.netease.com/ysf/5f6336cd056de200c6ae0efe90720350.wav\n" +
            "https://nos.netease.com/ysf/eac236a660a77f2644867000763fd043.wav\n" +
            "https://nos.netease.com/ysf/b73ed589932feec46ec1b6d0ed9f743d.wav\n" +
            "https://nos.netease.com/ysf/4a6ad2676c90bb36f87076a8779fe40e.wav\n" +
            "https://nos.netease.com/ysf/8cdd530e0d0a178582964ceababa71bf.wav\n" +
            "https://nos.netease.com/ysf/66bf0f5f7b485f0fbd350c4eab088312.wav\n" +
            "https://nos.netease.com/ysf/f25676455221475d531d848ac03c8173.wav\n" +
            "https://nos.netease.com/ysf/400d056ed79b47a0163412ccd442a9cd.wav\n" +
            "https://nos.netease.com/ysf/ea6a93d9abd9a3879b3821c6e6446190.wav\n" +
            "https://nos.netease.com/ysf/92e2dcdc5e39c01b9977ba862a83e880.wav\n" +
            "https://nos.netease.com/ysf/470d60e5efe37a4653928c783d7db353.wav\n" +
            "https://nos.netease.com/ysf/4486d85b287f4efd404f455ed0c516a1.wav\n" +
            "5.卖好车 域名：mhche appid：2414d7d53fbd4fccaecfedd3e9f0c556\n" +
            "https://nos.netease.com/ysf/869d0db23e9a402424c7e0e2fa7980eb.wav\n" +
            "https://nos.netease.com/ysf/f8fec4f9f4ccd3e94bd7b2840d6f4c1f.wav\n" +
            "https://nos.netease.com/ysf/a948915d6d69390499341ad2b7564c98.wav\n" +
            "https://nos.netease.com/ysf/d89c380c57751f2030c9cd7a7e6de57b.wav\n" +
            "https://nos.netease.com/ysf/8f6b08be1cf81d92c2c314d20d947b71.wav\n" +
            "https://nos.netease.com/ysf/c1bae9a9fd6b0ffde56318c499942d95.wav\n" +
            "https://nos.netease.com/ysf/412478b9630bb832de45cda7cf95c0dd.wav\n" +
            "https://nos.netease.com/ysf/9632a3337d9ad57f51f5ee4cc20754e1.wav\n" +
            "https://nos.netease.com/ysf/e8233074332324b5cfcc0b934932f11c.wav\n" +
            "https://nos.netease.com/ysf/92f3bf2fe759dad39d86d2216747612a.wav\n" +
            "https://nos.netease.com/ysf/7003c99ca5852ad48d33e262124fc2ea.wav\n" +
            "https://nos.netease.com/ysf/fbd0f9d49dd10d61876cf91d4a3ba249.wav\n" +
            "https://nos.netease.com/ysf/fd4beec780d6945fe8c5832b34064523.wav\n" +
            "https://nos.netease.com/ysf/48c22856e404994cc8284805f6fa4291.wav\n" +
            "https://nos.netease.com/ysf/7239ce3489d1bc7d60be5026782708af.wav\n" +
            "https://nos.netease.com/ysf/7b5d0a81f9c3db9491bc4b55b90ad814.wav\n" +
            "https://nos.netease.com/ysf/578ce5c76b2f362089faffde9525b998.wav\n" +
            "https://nos.netease.com/ysf/0c906dd7e2549a250e41a9f745c5b70f.wav\n" +
            "https://nos.netease.com/ysf/2b4fd388766f7a12f8775977fe100aed.wav\n" +
            "https://nos.netease.com/ysf/61f0efc608d8d5eecbd3f5ceaece24d8.wav\n" +
            "6.华润万家 域名：ewj01 appid：8aaf07085b3bb22e015b3cd9264e0222\n" +
            "https://nos.netease.com/ysf/b4a7f9aa7c341661ab0e9fb6b1ef48ef.wav\n" +
            "https://nos.netease.com/ysf/ce254c568cac5a56d833b897f8e69a05.wav\n" +
            "https://nos.netease.com/ysf/938728f10d9f4b13161b968e58ed1161.wav\n" +
            "https://nos.netease.com/ysf/53e322d694c82eac6fe1fa64037557ba.wav\n" +
            "https://nos.netease.com/ysf/535268e969ea5b76ca842cb5e2a32159.wav\n" +
            "https://nos.netease.com/ysf/2acd581c480c01e2208f95dc18e70595.wav\n" +
            "https://nos.netease.com/ysf/b3aae45d8d91afc45a18e8905142b820.wav\n" +
            "https://nos.netease.com/ysf/88e127ee9becaf0faed8fd4174062004.wav\n" +
            "https://nos.netease.com/ysf/b62b745ca9f6a33821d26e470b362380.wav\n" +
            "https://nos.netease.com/ysf/85a9380aaeae84b9606934b67d7da344.wav\n" +
            "https://nos.netease.com/ysf/544561792e0ad9754a40d43b9093de06.wav\n" +
            "https://nos.netease.com/ysf/c4a717d59c2d29053fc6cdbbba08cfce.wav\n" +
            "https://nos.netease.com/ysf/1885f3e951337efc9dd6318aaa526ec0.wav\n" +
            "https://nos.netease.com/ysf/611f74fa7adc36c7d49418fb14b3095a.wav\n" +
            "https://nos.netease.com/ysf/acb92889cbfd03041aa3ed64c97bec6d.wav\n" +
            "https://nos.netease.com/ysf/adf67cb1d6ce8b9601d71f35c0f0b55b.wav\n" +
            "https://nos.netease.com/ysf/83cfe466a2e0303ca33cf326eea30a0f.wav\n" +
            "https://nos.netease.com/ysf/60fa54740eb0e14bfafef48a664a97ff.wav\n" +
            "https://nos.netease.com/ysf/2350cef2ff571d5c505d72a5bf0217eb.wav\n" +
            "https://nos.netease.com/ysf/d09ccbe2675a0b94a19cb1be19603156.wav\n" +
            "7.环球优学 域名：qiyukfco appid：8a216da85adaebff015adb258590003f\n" +
            "https://nos.netease.com/ysf/e3cc635f9fc0f73cf7436de32902017b.wav\n" +
            "https://nos.netease.com/ysf/0c79ba8dde2ed473a82d98db277c5267.wav\n" +
            "https://nos.netease.com/ysf/6a373276268af883ac9d689323c43212.wav\n" +
            "https://nos.netease.com/ysf/a0b111883453b19d018f855a33ecfff2.wav\n" +
            "https://nos.netease.com/ysf/4e9ab84c1c96705980f2bff85d6506dc.wav\n" +
            "https://nos.netease.com/ysf/f8e917fa2989432d2af2b0d54d732e30.wav\n" +
            "https://nos.netease.com/ysf/0aabfc316f8a77a641ca29fab0a38fdb.wav\n" +
            "https://nos.netease.com/ysf/16eade84c3294fff1bc7ba9ac41921ea.wav\n" +
            "https://nos.netease.com/ysf/6481755dd9b68f624eb1aa9f228b70e2.wav\n" +
            "https://nos.netease.com/ysf/10543c7a8231aea622dc37a00ff43623.wav\n" +
            "https://nos.netease.com/ysf/d72a5df6049d834fa228b8da15cfda6e.wav\n" +
            "https://nos.netease.com/ysf/524991c064a865fcfa9232e7b8ad514d.wav\n" +
            "https://nos.netease.com/ysf/bb73f8285de56f7cd864c25975384d3f.wav\n" +
            "https://nos.netease.com/ysf/94054be5e851e8917d10f0b279c82ab7.wav\n" +
            "https://nos.netease.com/ysf/09f0e4aacc48715538398f8ed316ecdf.wav\n" +
            "https://nos.netease.com/ysf/8517d906c8231c1d0368cba71340c1cb.wav\n" +
            "https://nos.netease.com/ysf/987cd035f06b035de9c719e124f0f842.wav\n" +
            "https://nos.netease.com/ysf/991df5f4733d02a27256296d3289ef63.wav\n" +
            "https://nos.netease.com/ysf/caa219ae600e17cb1d058d9416a79cb1.wav\n" +
            "https://nos.netease.com/ysf/5e165bb4f5c01068c7fd6c7d9cb1474e.wav\n" +
            "8.杭州壹基因 域名：1genehealth appid：8a216da85b602cda015b7a9549ef0a3b\n" +
            "https://nos.netease.com/ysf/1496b178433de9c9ef259af9c1cf71f5.wav\n" +
            "https://nos.netease.com/ysf/62190f9ba631384f58bff3baa4173582.wav\n" +
            "https://nos.netease.com/ysf/b0c8a715be73cb749f89afc4d243ae14.wav\n" +
            "https://nos.netease.com/ysf/21cf97882efcdb18b5d931edf2e4c910.wav\n" +
            "https://nos.netease.com/ysf/521ad67c843cdc4845a38847076f6664.wav\n" +
            "https://nos.netease.com/ysf/01599cc8dae2872fe1adf6154f1f6474.wav\n" +
            "https://nos.netease.com/ysf/4de4b86a3c0b3b99b618bda27283e485.wav\n" +
            "https://nos.netease.com/ysf/a5d252e6289ffc595792834cdbc125e3.wav\n" +
            "https://nos.netease.com/ysf/e598fcd3a9649618fe269479a27e04fb.wav\n" +
            "https://nos.netease.com/ysf/68979d2b12a1f3bb877f518c8e1f88c4.wav\n" +
            "https://nos.netease.com/ysf/7775950daff65b01f68f2df6d0165253.wav\n" +
            "https://nos.netease.com/ysf/909ff58130370cd33cfe349435bbb117.wav\n" +
            "https://nos.netease.com/ysf/f8cdcf7bbf9fa65d5470d5cc74b8878c.wav\n" +
            "https://nos.netease.com/ysf/0c6a949dddc4fe22b8f0784c3f78acca.wav\n" +
            "https://nos.netease.com/ysf/4596b944b9822a92da1cc0a8c85b5e34.wav\n" +
            "https://nos.netease.com/ysf/464cc14805ef4f395242867f9212b661.wav\n" +
            "https://nos.netease.com/ysf/dbfc16da1aecc79392ad4467b39af3a9.wav\n" +
            "https://nos.netease.com/ysf/2db6d4d78d46e9ffa49d515a88669acb.wav\n" +
            "https://nos.netease.com/ysf/b24d19506afb788bea3ee1fcca6721a7.wav\n" +
            "https://nos.netease.com/ysf/c89cea727dc2a8759d54bc91e97f8711.wav\n" +
            "9. 吉利易云 域名：geelyit appid：8aaf07085cce64c1015ce81b2f1d08ab\n" +
            "https://nos.netease.com/ysf/5ebd30ba6816d07e0dd9a7bdd58fe953.wav\n" +
            "https://nos.netease.com/ysf/59a1dc9a02eb0cc4dbcfccd101251cf2.wav\n" +
            "https://nos.netease.com/ysf/fb1965a7b889959074822c7632c1e76f.wav\n" +
            "https://nos.netease.com/ysf/6828388a166e369dbcbc4cfa40995dfe.wav\n" +
            "https://nos.netease.com/ysf/a8907655e685f4372466bf27aececcb0.wav\n" +
            "https://nos.netease.com/ysf/fb492ea0a887018ac8bc6fa7df6ffdcf.wav\n" +
            "https://nos.netease.com/ysf/f32e9cad2b34b6660c560c1f46d1a30d.wav\n" +
            "https://nos.netease.com/ysf/482ef3e9253e2368f83bfc81524bdbfb.wav\n" +
            "https://nos.netease.com/ysf/2fcb31f0238ec00d58ed60971db89675.wav\n" +
            "https://nos.netease.com/ysf/a6e35ae90cfb7a1ac76dac9fb5cf81c1.wav\n" +
            "https://nos.netease.com/ysf/74f234f381436226d4dd9d0c6cde7166.wav\n" +
            "https://nos.netease.com/ysf/e6881c9e6c7212d8b28321d734ec9c6a.wav\n" +
            "https://nos.netease.com/ysf/138b5bb7f66f531674d32838ed9c1ae5.wav\n" +
            "https://nos.netease.com/ysf/0cfc16c2493add7cc9a5476014a2e25f.wav\n" +
            "https://nos.netease.com/ysf/9b2330663bcc0e9b80081f603ff32468.wav\n" +
            "https://nos.netease.com/ysf/7178d18f82e2f1bfbcc3db362ffc8e97.wav\n" +
            "https://nos.netease.com/ysf/f4d0fe4c7fed87e8edbe2a7ba412b4ac.wav\n" +
            "https://nos.netease.com/ysf/f8028b4a57cd87ed9c9c401045300067.wav\n" +
            "https://nos.netease.com/ysf/192a6b9db32bd057c7775f3cf9f7d0aa.wav\n" +
            "https://nos.netease.com/ysf/be6d97504067d5ab18b2d47f547c68d5.wav\n" +
            "10. 青团社 域名：qingtuan appid：8a216da85c1ababd015c1adba077003c\n" +
            "https://nos.netease.com/ysf/da04f587f14a4191d1d7353f981bb5df.wav\n" +
            "https://nos.netease.com/ysf/7c186a5d2f6de07186264cf5945b60ee.wav\n" +
            "https://nos.netease.com/ysf/fd7e1b8cf46dae089d013577d6eefcc6.wav\n" +
            "https://nos.netease.com/ysf/bab72859a6d5ea7758b9565a2ea5ba20.wav\n" +
            "https://nos.netease.com/ysf/73d6b3bc45bf16f749ba3aca9c9b5531.wav\n" +
            "https://nos.netease.com/ysf/49419e80a4395cc092e69044a8434f93.wav\n" +
            "https://nos.netease.com/ysf/98f5a24e1a894be5063db5b40de58548.wav\n" +
            "https://nos.netease.com/ysf/011c1c8922e6e4adc2d154a042075e30.wav\n" +
            "https://nos.netease.com/ysf/6405f73bafc5ee899ecb509bf00521ee.wav\n" +
            "https://nos.netease.com/ysf/327b20c061312565ef57f4359e818f85.wav\n" +
            "https://nos.netease.com/ysf/d24ebf55f48583c6084b648f65ba46a7.wav\n" +
            "https://nos.netease.com/ysf/cc3df305846276b90a1b7e6944ac7d82.wav\n" +
            "https://nos.netease.com/ysf/2b66dfad0ba25358f1d72a863f4c7312.wav\n" +
            "https://nos.netease.com/ysf/c463bd10a552831fa941e36e04d04b95.wav\n" +
            "https://nos.netease.com/ysf/dc99e540b38e182c0ee9340543edf8c7.wav\n" +
            "https://nos.netease.com/ysf/102601109daf72d8423b86283a38b992.wav\n" +
            "https://nos.netease.com/ysf/a0fc6cebb79bdde80d71473f8e877ce6.wav\n" +
            "https://nos.netease.com/ysf/b916eec4b6a1bdba864214824bad0791.wav\n" +
            "https://nos.netease.com/ysf/69e42142dfcd975c332394875ae2d908.wav\n" +
            "https://nos.netease.com/ysf/7e6066cd31cd0d02fe5c3ca8d2ad07d7.wav";

    private static Map<String, String> snMap = new HashMap<>();
    private static int total = 0;
    private static int success = 0;

    public static void asr() {
        String[] sources = source.split("\n");
        for (String url : sources) {
            if (!url.startsWith("http")) {
                continue;
            }

            total++;
            String sn = request(url);
            if (sn != null) {
                success++;
                snMap.put(sn, url);
            }
        }

        LOGGER.info("\n***" + "send request finish, total: " + total + ", success: " + success + "***\n");
    }

    private static String request(String data) {
        String url = "http://api.vop.netease.com/callback/asr_api?cuid=wangchenyan&token=0a3318fed788ccbe4c24b15bef757fc6&callback=http://114.67.154.97:8080/api/asr";
        String result;
        try {
            result = HttpClient.postText(url, null, data);
            JSONObject object = JSON.parseObject(result);
            if (object.getIntValue("ret_code") == 1001) {
                return object.getString("sn");
            } else {
                LOGGER.error("trans failed, url: " + data + ", msg: " + object.getString("ret_msg"));
            }
        } catch (IOException e) {
            LOGGER.error("trans failed, url: " + data + ", msg: " + e.getMessage());
        }
        return null;
    }
}
