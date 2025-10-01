package com.web3.constant;

import java.util.Set;

public interface TaskConstant {

    interface Monad {
        String SWITCH = "monad-switch";

        String FAUCET = "monad-faucet";
        String FAUCET_MORKIE = "monad-faucet-morkie";
        String FAUCET_TALENTUM = "monad-faucet-talentum";
        String FAUCET_DUSTED = "monad-faucet-dusted";
        String FAUCET_GLACIERFI = "monad-faucet-glacierfi";
        String FAUCET_MEME = "monad-faucet-meme";

        String NFT_TALENTUM = "monad-NFT-talentum";
        String NFT_MONAI = "monad-NFT-monai";
        String NFT_MAGICEDEN = "monad-NFT-magiceden";
        String NFT_MORKIE = "monad-NFT-morkie";
        String NFT_NERZO = "monad-NFT-nerzo";


        String VISIT_TALENTUM = "monad-visit-talentum";
        String TALENTUM_STREASK = "monad-talentum-streak";

        String A_PRIOR = "monad-aprior";
        String BEAN = "monad-bean";
        String AICRAFT = "monad-aicraft";
        String BEBOP = "monad-bebop";
        String SHMONAD = "monad-shmonad";
        String KINZA = "monad-kinza";
        String OWLTO = "monad-owlto";
        String MINTAIR = "monad-mintair";
        String KURU = "monad-kuru";
        String KINTSU = "monad-kintsu";
        String MONORAIL = "monad-monorail";
        String CLOBER = "monad-clober";
        String ATLANTIS = "monad-atlantis";
        String MONAD = "monad.xyz";
    }

    Set<String> MONAD_FAUCETS = Set.of(
            Monad.FAUCET,
            Monad.FAUCET_MORKIE,
            Monad.FAUCET_TALENTUM,
            Monad.FAUCET_DUSTED,
            Monad.FAUCET_GLACIERFI,
            Monad.FAUCET_MEME
    );


    interface Depin {
        String DESPEED = "despeed-sign";
        String BLOCK_MESH = "blockMesh-liveness";
    }

    interface Sepolia {
        String FAUCET = "sepolia-faucet";
        String QUICK_NODE = "sepolia-quick-node";
    }


    interface Twitter {
        String BROWSE = "browse";
    }

}
