# Implementação do Grey Wolf Optimizer (GWO)

Este repositório contém uma implementação do **Grey Wolf Optimizer (GWO)**, um algoritmo de otimização meta-heurístico inspirado na hierarquia social e na estratégia de caça dos lobos-cinzentos na natureza.

## Principais Funcionalidades
- **Função Objetivo Personalizável**: Adapte facilmente o otimizador para resolver uma ampla variedade de problemas definindo sua própria função de fitness.
- **Parâmetros Escaláveis**: Ajuste o número de lobos, iterações e dimensões do problema para melhorar o desempenho da otimização.
- **Eficiente e Simples**: Projetado para ser leve e de alto desempenho, adequado para tarefas acadêmicas e práticas.

## O que é o GWO?
O Grey Wolf Optimizer imita a hierarquia de liderança e o comportamento de caça dos lobos-cinzentos, com quatro papéis principais:
1. **Alpha**: O líder, representando a melhor solução encontrada.
2. **Beta**: A segunda melhor solução, auxiliando o Alpha na tomada de decisão.
3. **Delta**: A terceira melhor solução, que guia os lobos de menor hierarquia.
4. **Omega**: O restante da população, que segue as soluções de maior hierarquia.

Por meio de processos de cercar a presa, explorar e atacar, o algoritmo melhora iterativamente as soluções da população até convergir para um resultado ótimo ou quase ótimo.

## Aplicações
O algoritmo GWO pode ser aplicado em:
- Otimização de funções matemáticas
- Problemas de design em engenharia
- Ajuste de hiperparâmetros em aprendizado de máquina
- Problemas de otimização combinatória
