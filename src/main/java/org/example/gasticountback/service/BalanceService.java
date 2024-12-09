package org.example.gasticountback.service;

import org.example.gasticountback.DTOs.CalcularBalanceDTO;
import org.example.gasticountback.DTOs.UsuarioBalanceDTO;
import org.example.gasticountback.DTOs.VerBalanceDTO;
import org.example.gasticountback.entity.Gasto;
import org.example.gasticountback.entity.Usuario;
import org.example.gasticountback.repository.IGastoRepository;
import org.example.gasticountback.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BalanceService {
    @Autowired
    private IGastoRepository gastoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;


    public List<UsuarioBalanceDTO> calcularBalancesPorGrupo(Integer grupoId) {
        List<Gasto> gastosGrupo = gastoRepository.findByGrupoId(grupoId);
        List<Usuario> usuarios = usuarioRepository.findByGrupos_Id(grupoId);

        double totalGastosGrupo = gastosGrupo.stream().mapToDouble(Gasto::getPrecio).sum();
        int numeroUsuarios = usuarios.size();

        if (numeroUsuarios == 0) {
            throw new IllegalArgumentException("El grupo no tiene usuarios.");
        }

        double balancePorUsuario = totalGastosGrupo / numeroUsuarios;

        List<UsuarioBalanceDTO> balances = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            List<Gasto> gastosUsuario = gastoRepository.findByUsuarioIdAndGrupoId(usuario.getId(), grupoId);
            double totalGastosUsuario = gastosUsuario.stream().mapToDouble(Gasto::getPrecio).sum();
            double balance = balancePorUsuario - totalGastosUsuario;

            String mensaje;
            if (balance < 0) {
                mensaje = "Le Deben ";
                balance = -balance;
            } else if (balance > 0) {
                mensaje = "Debe ";
            } else {
                mensaje = "Estás en paz y a salvo";
            }
            double balanceFormateado = Math.round(balance * 100.0) / 100.0;
            balances.add(new UsuarioBalanceDTO(usuario.getNombre(), mensaje, balanceFormateado, usuario.getFoto()));
        }

        return balances;
    }


/*
    public List<VerBalanceDTO> calcularBalance(CalcularBalanceDTO calcularBalanceDTO) {
        Integer grupoId = calcularBalanceDTO.getGrupoId();
        List<Gasto> gastos = gastoRepository.findByGrupoId(grupoId);
        List<Usuario> usuarios = usuarioRepository.findByGrupoId(grupoId);

        Map<String, VerBalanceDTO> balances = new HashMap<>();
        for (Usuario usuario : usuarios) {
            balances.put(usuario.getNombre(), new VerBalanceDTO(usuario.getNombre(), 0.0));
        }

        for (Gasto gasto : gastos) {
            double share = gasto.getMonto() / gasto.getParticipantes().size();
            for (Usuario participante : gasto.getParticipantes()) {
                VerBalanceDTO balance = balances.get(participante.getNombre());
                balance.setBalance(balance.getBalance() - share);
            }
            VerBalanceDTO balancePayer = balances.get(gasto.getPagador().getNombre());
            balancePayer.setBalance(balancePayer.getBalance() + gasto.getMonto());
        }

        return new ArrayList<>(balances.values());
    }

    public void simplifyDebts(List<VerBalanceDTO> balances) {
        List<VerBalanceDTO> creditors = new ArrayList<>();
        List<VerBalanceDTO> debtors = new ArrayList<>();

        for (VerBalanceDTO balance : balances) {
            if (balance.getBalance() > 0) {
                creditors.add(balance);
            } else if (balance.getBalance() < 0) {
                debtors.add(balance);
            }
        }

        int i = 0, j = 0;
        while (i < creditors.size() && j < debtors.size()) {
            VerBalanceDTO creditor = creditors.get(i);
            VerBalanceDTO debtor = debtors.get(j);

            double amount = Math.min(creditor.getBalance(), -debtor.getBalance());

            System.out.println(debtor.getNombre() + " paga $" + amount + " a " + creditor.getNombre());

            creditor.setBalance(creditor.getBalance() - amount);
            debtor.setBalance(debtor.getBalance() + amount);

            if (creditor.getBalance() == 0) i++;
            if (debtor.getBalance() == 0) j++;
        }
    }
 */
}