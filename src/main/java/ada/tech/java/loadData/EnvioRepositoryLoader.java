//package ada.tech.java.loadData;
//
//import ada.tech.java.Repository.EnvioRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.IntStream;
//
//@Component
//@RequiredArgsConstructor
//public class EnvioRepositoryLoader implements CommandLineRunner {
//    private final EnvioRepository envioRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        if(envioRepository.count()==0) {
//            createItems();
//        }
//    }
//
//    private void createItems() {
//        IntStream.range(1, 51)
//                .mapToObj(this::generateItem).forEach(envioRepository::save);
//    }

//    private Item generateItem(int i) {
//        if(i==0){
//            i = 1;
//        }
//        Random random = new Random();
//
//        Item item = new Item();
//        item.setIdentificador(UUID.randomUUID().toString());
//        item.setValorUnitario(BigDecimal.valueOf(random.nextInt(i)* 100L));
//        item.setSku(String.format("Z%04d", i));
//        item.setQuantidadeEstoque(i*100);
//
//        return item;
//    }

//}
